module Api
  class BaseController < ActionController::Base
    include Pundit
    protect_from_forgery

    before_action :ensure_authenticated
    skip_before_action :verify_authenticity_token

    rescue_from ActiveRecord::RecordNotFound, with: :render_not_found
    rescue_from Pundit::NotAuthorizedError, with: :render_forbidden
    rescue_from ActionController::ParameterMissing, with: :render_missing

    def current_user
      @current_user ||= headers_token&.user
    end

    def headers_token
      @headers_token ||= Token.find_by(token: request.headers['Authorization'])
    end

    def ensure_authenticated
      return if current_user

      render json: { errors: { token: ['is invalid'] } }, status: :unauthorized
    end

    def render_forbidden
      render json: { errors: { resource: ['is forbidden'] } },
             status: :forbidden
    end

    def render_not_found
      render json: { errors: { resource: ["doesn't exist"] } }, status: :not_found
    end

    def render_missing(exception)
      render json: { errors: { exception.param => ['is missing'] } },
             status: :bad_request
    end
  end
end

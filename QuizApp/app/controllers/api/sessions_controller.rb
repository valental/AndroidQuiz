module Api
  class SessionsController < Api::BaseController
    skip_before_action :ensure_authenticated, only: :create

    # POST /api/session
    def create
      user = User.find_by(email: session_params[:email])

      if user&.authenticate(session_params[:password])
        if(!user.has_registered)
          render json: { errors: { registration: ['has not been confirmed'] } },
                 status: :bad_request
        else
          render json: Session.new(token: user.token, user: user),
                 status: :created
        end
      else
        render json: { errors: { credentials: ['are invalid'] } },
               status: :bad_request
      end
    end

    # DELETE /api/session
    def destroy
      current_user.regenerate_token

      head :no_content
    end

    private

    def session_params
      params.require(:session).permit(:email, :password)
    end
  end
end

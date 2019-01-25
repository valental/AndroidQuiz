class RegistrationController < ApplicationController
  # GET /registrate?token=:token
  def registrate
    token = params[:token]

    user = User.find_by(registration_token: token)

    if user
      user.has_registered = true
      render json: { registered: 'true' }
    else
      render json: { error: 'registration token is not valid' }, status: :not_found
    end
  end
end

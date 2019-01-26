class RegistrationController < ApplicationController
  # GET /registrate?token=:token
  def registrate
    token = params[:token]

    @user = User.find_by(registration_token: token)

    if @user
      @user.has_registered = true
      render 'confirmed'
    else
      render 'invalid'
    end
  end
end

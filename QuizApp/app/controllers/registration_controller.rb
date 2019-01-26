class RegistrationController < ApplicationController
  # GET /registration/confirm?token=:token
  def confirm
    token = params[:token]

    @user = User.find_by(registration_token: token)

    if @user
      @user.update(has_registered: true)

      render 'confirmed'
    else
      render 'invalid'
    end
  end
end

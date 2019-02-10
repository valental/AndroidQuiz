class UserMailer < ApplicationMailer
  def invite_email
    @user = User.find(params[:id])
    @url  = ENV['server_url'] + "/registration/confirm?token=" + @user.registration_token

    mail(to: @user.email, subject: 'Invite to Quiz app')
  end
end

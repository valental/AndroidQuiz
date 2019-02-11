module Api
  class SessionsController < Api::BaseController
    skip_before_action :ensure_authenticated, only: :create

    # POST /api/session
    def create
      user = User.find_by(username: session_params[:username])

      if user&.authenticate(session_params[:password])
        if(!user.has_registered)
          render json: { errors: { email: ['has not been confirmed'] } },
                 status: :unprocessable_entity
        else
          user.regenerate_token
          Token.create(user: user, token: user.token)

          render json: { id: user.id, username: user.username, token: user.token,
                         sport: user.sport, geography: user.geography, science: user.science,
                         history_art: user.history_art, movie: user.movie },
                 status: :created
        end
      else
        render json: { errors: { password: ['is incorrect'] } },
               status: :bad_request
      end
    end

    # DELETE /api/session
    def destroy
      headers_token.delete

      head :no_content
    end

    private

    def session_params
      params.permit(:username, :password)
    end
  end
end

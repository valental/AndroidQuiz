module Api
  class UsersController < Api::BaseController
    skip_before_action :ensure_authenticated, only: [:create, :registrate]

    # GET /api/users
    def index
      render json:
        User.order(:email)
    end

    # GET /api/users/:id
    def show
      authorize user

      render json: current_user
    end

    # POST /api/users
    def create
      reg_token = SecureRandom.base58(32)
      user = User.new(user_params, registration_token: reg_token)

      if user.save
        render json: user, status: :created
      else
        render json: { errors: user.errors }, status: :bad_request
      end
    end

    # PUT /api/users/:id
    def update
      authorize user

      if current_user.update(user_params)
        render json: current_user
      else
        render json: { errors: current_user.errors }, status: :bad_request
      end
    end

    # DELETE /api/users/:id
    def destroy
      authorize user

      current_user.destroy

      head :no_content
    end

    # GET /api/users/registrate?token=:token
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

    private

    def user_params
      params.require(:user).permit(:first_name,
                                   :last_name,
                                   :email,
                                   :password)
    end

    def user
      User.find(params[:id])
    end
  end
end

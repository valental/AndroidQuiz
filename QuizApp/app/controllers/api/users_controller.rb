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

      user = User.new(user_params)
      user.registration_token = reg_token

      if user.save
        UserMailer.with(id: user.id).invite_email.deliver

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

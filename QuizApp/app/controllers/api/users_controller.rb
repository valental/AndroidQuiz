module Api
  class UsersController < Api::BaseController
    skip_before_action :ensure_authenticated, only: [:create, :scores]

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
      user.password = params[:password] || user.password
      user.registration_token = reg_token

      if user.save
        UserMailer.with(id: user.id).invite_email.deliver

        render json: { id: user.id, username: user.username, email: user.email }, status: :created
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

    # POST /api/level body { category: history_art, lvl: 3, time: time }
    def level
      category = current_user.try(params[:category]) ? params[:category] : nil
      lvl = ['1', '2', '3', 1, 2, 3].include?(params[:lvl]) ? params[:lvl].to_i : nil
      time = params[:time]

      current_time = current_user["#{category}_#{lvl}"]

      if category && lvl
        if(lvl > current_user[category] - 1)
          current_user[category] = lvl + 1
        end

        if(current_time.nil? || time < current_time)
          current_user["#{category}_#{lvl}"] = time
        end

        if current_user.save!
          render json: { }, status: :ok
        else
          render json: { }, status: :bad_request
        end
      else
        render json: { }, status: :bad_request
      end
    end

    # GET /api/scores
    def scores
      r_movie = User.best_movie
      user_movie_1 = r_movie[0] ? r_movie[0].username : '-'
      user_movie_2 = r_movie[1] ? r_movie[1].username : '-'
      user_movie_3 = r_movie[2] ? r_movie[2].username : '-'

      r_science = User.best_science
      user_science_1 = r_science[0] ? r_science[0].username : '-'
      user_science_2 = r_science[1] ? r_science[1].username : '-'
      user_science_3 = r_science[2] ? r_science[2].username : '-'

      r_history_art = User.best_history_art
      user_history_art_1 = r_history_art[0] ? r_history_art[0].username : '-'
      user_history_art_2 = r_history_art[1] ? r_history_art[1].username : '-'
      user_history_art_3 = r_history_art[2] ? r_history_art[2].username : '-'

      r_geography = User.best_geography
      user_geography_1 = r_geography[0] ? r_geography[0].username : '-'
      user_geography_2 = r_geography[1] ? r_geography[1].username : '-'
      user_geography_3 = r_geography[2] ? r_geography[2].username : '-'

      r_sport = User.best_sport
      user_sport_1 = r_sport[0] ? r_sport[0].username : '-'
      user_sport_2 = r_sport[1] ? r_sport[1].username : '-'
      user_sport_3 = r_sport[2] ? r_sport[2].username : '-'

      render json: { user_movie_1: user_movie_1, user_movie_2: user_movie_2, user_movie_3: user_movie_3,
                     user_science_1: user_science_1, user_science_2: user_science_2, user_science_3: user_science_3,
                     user_history_art_1: user_history_art_1, user_history_art_2: user_history_art_2, user_history_art_3: user_history_art_3,
                     user_geography_1: user_geography_1, user_geography_2: user_geography_2, user_geography_3: user_geography_3,
                     user_sport_1: user_sport_1, user_sport_2: user_sport_2, user_sport_3: user_sport_3 }, status: :ok
    end

    private

    def user_params
      params.permit(:email, :username, :password)
    end

    def user
      User.find(params[:id])
    end
  end
end

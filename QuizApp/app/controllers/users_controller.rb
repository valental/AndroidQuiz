class UsersController < ApplicationController
  def index
    @users = User.order(score: :desc).limit(10)
  end
end

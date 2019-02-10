class UsersController < ApplicationController
  def index
    @users = User.best_all.limit(10)
  end
end

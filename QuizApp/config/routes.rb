Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  namespace :api do
    resources :users, except: [:new, :edit]

    resource :session, only: [:create, :destroy]
  end
end

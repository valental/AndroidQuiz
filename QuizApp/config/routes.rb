Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  namespace :api do
    resources :users, except: [:new, :edit]
    get '/scores' => 'users#scores'
    post '/level' => 'users#level'

    resource :session, only: [:create, :destroy]
  end

  get '/registration/confirm' => 'registration#confirm'

  root to: 'users#index'
end

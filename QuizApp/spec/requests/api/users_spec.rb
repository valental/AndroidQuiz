RSpec.describe 'Users API', type: :request do
  include TestHelpers::JsonResponse

  let(:user) { FactoryBot.create(:user) }
  let(:unauthorized_user) { FactoryBot.create(:user) }

  describe 'GET /api/users' do
    let(:first) do
      FactoryBot.create(:user, first_name: 'John',
                               last_name: 'Smith',
                               email: 'john.smith@gmail.com')
    end

    let(:second) do
      FactoryBot.create(:user, first_name: 'Jane',
                               last_name: 'Smith',
                               email: 'jane.smith@gmail.com')
    end

    before do
      first
      second
    end

    context 'when user is unauthenticated' do
      it 'returns status code 401 unauthorized' do
        get '/api/users'

        expect(response).to have_http_status(:unauthorized)
      end

      it 'returns errors' do
        get '/api/users'

        expect(json_body).to include(:errors)
      end
    end

    context 'when user is authenticated' do
      it 'returns status code 200 OK' do
        get '/api/users',
            headers: { 'Authorization' => first.token }

        expect(response).to have_http_status(:ok)
      end

      it 'returns list of users' do
        get '/api/users',
            headers: { 'Authorization' => first.token }

        expect(json_body).to include(:users)
      end

      it 'returns correct number of users' do
        get '/api/users',
            headers: { 'Authorization' => first.token }

        expect(length).to eq(2)
      end

      it 'sorts users by email' do
        get '/api/users',
            headers: { 'Authorization' => first.token }

        expect(ids).to eq([second.id, first.id])
      end
    end
  end

  describe 'GET /api/users/:id' do
    context 'when user is unauthenticated' do
      it 'returns status code 401 UNAUTHORIZED' do
        get "/api/users/#{user.id}"

        expect(response).to have_http_status(:unauthorized)
      end

      it 'returns errors' do
        get "/api/users/#{user.id}"

        expect(json_body).to include(:errors)
      end
    end

    context 'when user is unauthorized' do
      it 'returns status code 403 FORBIDDEN' do
        get "/api/users/#{user.id}",
            headers: { 'Authorization' => unauthorized_user.token }

        expect(response).to have_http_status(:forbidden)
      end

      it 'returns errors' do
        get "/api/users/#{user.id}",
            headers: { 'Authorization' => unauthorized_user.token }

        expect(json_body).to include(:errors)
      end
    end

    context 'when user is authorized' do
      it 'returns status code 200 OK' do
        get "/api/users/#{user.id}",
            headers: { 'Authorization' => user.token }

        expect(response).to have_http_status(:ok)
      end

      it 'returns a single user' do
        get "/api/users/#{user.id}",
            headers: { 'Authorization' => user.token }

        expect(json_body).to include(:user)
      end
    end
  end

  describe 'POST /api/users' do
    let(:user_params) { FactoryBot.attributes_for(:user) }

    context 'when params are valid' do
      it 'returns status code 201 CREATED' do
        post '/api/users',
             params: user_params

        expect(response).to have_http_status :created
      end

      it 'increases number of users by one' do
        expect do
          post '/api/users',
               params: user_params
        end.to change(User, :count).by(1)
      end

      it 'user can authenticate' do
        post '/api/users',
             params: user_params.merge(password: 'password')

        user = User.find(json_body[:id])

        expect(user.authenticate('password')).not_to be_falsey
      end
    end

    context 'when params are invalid' do
      it 'returns 400 BAD REQUEST' do
        post '/api/users',
             params: { user: user_params.merge(email: '') }

        expect(response).to have_http_status(:bad_request)
      end

      it 'returns errors' do
        post '/api/users',
             params: { user: user_params.merge(email: '') }

        expect(json_body).to include(:errors)
      end
    end
  end

  describe 'PUT /api/users/:id' do
    context 'when user is unauthenticated' do
      it 'returns status code 401 UNAUTHORIZED' do
        put "/api/users/#{user.id}",
            params: { user: { email: 'new.mail@example.com' } }

        expect(response).to have_http_status(:unauthorized)
      end

      it 'returns errors' do
        put "/api/users/#{user.id}",
            params: { user: { email: 'new.mail@example.com' } }

        expect(json_body).to include(:errors)
      end
    end

    context 'when user is unauthorized' do
      it 'returns status code 403 FORBIDDEN' do
        put "/api/users/#{user.id}",
            params: { user: { email: 'new.mail@example.com' } },
            headers: { 'Authorization' => unauthorized_user.token }

        expect(response).to have_http_status(:forbidden)
      end

      it 'returns errors' do
        put "/api/users/#{user.id}",
            params: { user: { email: 'new.mail@example.com' } },
            headers: { 'Authorization' => unauthorized_user.token }

        expect(json_body).to include(:errors)
      end
    end

    context 'when params are valid' do
      it 'returns status code 200 OK' do
        put "/api/users/#{user.id}",
            params: { user: { email: 'new.mail@example.com' } },
            headers: { 'Authorization' => user.token }

        expect(response).to have_http_status(:ok)
      end

      it 'updates user' do
        put "/api/users/#{user.id}",
            params: { email: 'new.mail@example.com' },
            headers: { 'Authorization' => user.token }

        expect(json_body[:user]).to include(email: 'new.mail@example.com')
      end

      it 'can update password' do
        put "/api/users/#{user.id}",
            params: { user: { password: 'abc' } },
            headers: { 'Authorization' => user.token }

        user = User.find(json_body[:user][:id])

        expect(user.authenticate('abc')).not_to be_falsey
      end
    end

    context 'when params are invalid' do
      it 'returns 400 BAD REQUEST' do
        put "/api/users/#{user.id}",
            params: { email: '' },
            headers: { 'Authorization' => user.token }

        expect(response).to have_http_status(:bad_request)
      end

      it 'returns errors' do
        put "/api/users/#{user.id}",
            params: { email: '' },
            headers: { 'Authorization' => user.token }

        expect(json_body).to include(:errors)
      end

      it 'cannot set password to nil' do
        put "/api/users/#{user.id}",
            params: { password: nil },
            headers: { 'Authorization' => user.token }

        expect(response).to have_http_status(:bad_request)
      end
    end
  end

  describe 'DELETE /api/users/:id' do
    before { user }

    context 'when user is unauthenticated' do
      it 'returns status code 401 UNAUTHORIZED' do
        delete "/api/users/#{user.id}"

        expect(response).to have_http_status(:unauthorized)
      end

      it 'returns errors' do
        delete "/api/users/#{user.id}"

        expect(json_body).to include(:errors)
      end
    end

    context 'when user is unauthorized' do
      it 'returns status code 403 FORBIDDEN' do
        delete "/api/users/#{user.id}",
               headers: { 'Authorization' => unauthorized_user.token }

        expect(response).to have_http_status(:forbidden)
      end

      it 'returns errors' do
        delete "/api/users/#{user.id}",
               headers: { 'Authorization' => unauthorized_user.token }

        expect(json_body).to include(:errors)
      end
    end

    context 'when user is authorized' do
      it 'returns 204 NO CONTENT' do
        delete "/api/users/#{user.id}",
               headers: { 'Authorization' => user.token }

        expect(response).to have_http_status(:no_content)
      end

      it 'deletes the user' do
        expect do
          delete "/api/users/#{user.id}",
                 headers: { 'Authorization' => user.token }
        end.to change(User, :count).by(-1)
      end
    end
  end
end

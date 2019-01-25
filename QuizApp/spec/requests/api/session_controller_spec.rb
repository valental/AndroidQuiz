RSpec.describe 'Session API', type: :request do
  include TestHelpers::JsonResponse

  let(:credentials) { { email: 'user@email.com', password: 'pass', has_registered: true } }
  let(:user) { FactoryBot.create(:user, credentials) }

  before { user }

  describe 'POST /api/session' do
    context 'with valid credentials' do
      it 'returns status code 201 CREATED' do
        post '/api/session', params: { session: credentials }

        expect(response).to have_http_status(:created)
      end

      it 'response contains token and user' do
        post '/api/session', params: { session: credentials }

        expect(json_body[:session]).to include(:token, :user)
      end

      it 'response token value matches database value' do
        post '/api/session', params: { session: credentials }

        user = User.find(json_body[:session][:user][:id])

        expect(user.token).to eq(json_body[:session][:token])
      end
    end

    context 'with invalid credentials' do
      let(:invalid_credentials) { { email: 'user@email.com', password: '' } }

      it 'fails with status code 400 BAD_REQUEST' do
        post '/api/session', params: { session: invalid_credentials }

        expect(response).to have_http_status(:bad_request)
      end

      it 'returns errors' do
        post '/api/session', params: { session: invalid_credentials }

        expect(json_body).to include(:errors)
      end
    end
  end

  describe 'DELETE /api/session' do
    context 'when user is unauthenticated' do
      it 'returns status code UNAUTHORIZED 401' do
        delete '/api/session'

        expect(response).to have_http_status(:unauthorized)
      end

      it 'returns errors' do
        delete '/api/session'

        expect(json_body).to include(:errors)
      end
    end

    context 'when user is authenticated' do
      it 'returns status code NO_CONTENT 204' do
        delete '/api/session',
               headers: { 'Authorization' => user.token }

        expect(response).to have_http_status(:no_content)
      end

      it 'changes users token' do
        expect do
          delete '/api/session',
                 headers: { 'Authorization' => user.token }

          user.reload
        end.to change(user, :token)
      end
    end
  end
end

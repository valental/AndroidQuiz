RSpec.describe UserPolicy do
  subject { described_class.new(current_user, user) }

  let(:user) { FactoryBot.create(:user) }

  context 'when logged in as normal user' do
    let(:current_user) { FactoryBot.create(:user) }

    it { is_expected.to permit_action(:index) }
    it { is_expected.to forbid_action(:show) }
    it { is_expected.to permit_action(:create) }
    it { is_expected.to forbid_action(:update) }
    it { is_expected.to forbid_action(:destroy) }
  end

  context 'when logged in as resource owner' do
    let(:current_user) { user }

    it { is_expected.to permit_action(:index) }
    it { is_expected.to permit_action(:show) }
    it { is_expected.to permit_action(:create) }
    it { is_expected.to permit_action(:update) }
    it { is_expected.to permit_action(:destroy) }
  end
end

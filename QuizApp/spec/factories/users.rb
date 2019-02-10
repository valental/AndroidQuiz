FactoryBot.define do
  factory :user do |f|
    f.sequence(:email) { Faker::Internet.email }
    f.sequence(:username) { Faker::Internet.user_name }
    f.password { 'abc' }
  end
end

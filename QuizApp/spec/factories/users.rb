FactoryBot.define do
  factory :user do |f|
    f.sequence(:email) { Faker::Internet.email }
    f.first_name { 'Name' }
    f.last_name { 'Surname' }
    f.password { 'abc' }
  end
end

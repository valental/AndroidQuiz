class UserSerializer < ActiveModel::Serializer
  attribute :id
  attribute :email
  attribute :first_name
  attribute :last_name
end

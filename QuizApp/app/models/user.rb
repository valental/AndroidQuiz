class User < ApplicationRecord
  has_secure_password
  has_secure_token

  validates :email, presence: true,
                    uniqueness: { case_sensitive: false },
                    format: /\A[^@\s]+@([^@\s]+\.)+[^@\s]+\z/

  validates :first_name, presence: true,
                         length: { minimum: 2 }

  scope :query, lambda { |query|
    where('(first_name || last_name || email) ILIKE ?', "%#{query}%")
  }
end

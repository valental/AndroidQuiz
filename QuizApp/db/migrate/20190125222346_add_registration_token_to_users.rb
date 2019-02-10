class AddRegistrationTokenToUsers < ActiveRecord::Migration[5.2]
  def change
    add_column :users, :registration_token, :string
  end
end

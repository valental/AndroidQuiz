class AddPasswordDigestToUsers < ActiveRecord::Migration[5.2]
  def up
    add_column :users, :password_digest, :string, null: false, default: ''

    User.all.each do |user|
      user.password = 'abc'
      user.save!
    end
  end

  def down
    remove_column :users, :password_digest
  end
end

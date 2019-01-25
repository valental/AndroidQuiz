class AddHasRegisteredToUsers < ActiveRecord::Migration[5.2]
  def change
    add_column :users, :has_registered, :boolean, default: false
  end
end

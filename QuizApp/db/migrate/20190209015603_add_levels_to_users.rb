class AddLevelsToUsers < ActiveRecord::Migration[5.2]
  def change
    add_column :users, :geography, :integer, null: false, default: 1
    add_column :users, :sport, :integer, null: false, default: 1
    add_column :users, :movie, :integer, null: false, default: 1
    add_column :users, :history_art, :integer, null: false, default: 1
    add_column :users, :science, :integer, null: false, default: 1
  end
end

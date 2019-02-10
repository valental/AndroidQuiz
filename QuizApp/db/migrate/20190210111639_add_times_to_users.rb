class AddTimesToUsers < ActiveRecord::Migration[5.2]
  def change
    add_column :users, :sport_1, :decimal, null: true
    add_column :users, :sport_2, :decimal, null: true
    add_column :users, :sport_3, :decimal, null: true
    add_column :users, :history_art_1, :decimal, null: true
    add_column :users, :history_art_2, :decimal, null: true
    add_column :users, :history_art_3, :decimal, null: true
    add_column :users, :geography_1, :decimal, null: true
    add_column :users, :geography_2, :decimal, null: true
    add_column :users, :geography_3, :decimal, null: true
    add_column :users, :movie_1, :decimal, null: true
    add_column :users, :movie_2, :decimal, null: true
    add_column :users, :movie_3, :decimal, null: true
    add_column :users, :science_1, :decimal, null: true
    add_column :users, :science_2, :decimal, null: true
    add_column :users, :science_3, :decimal, null: true
  end
end

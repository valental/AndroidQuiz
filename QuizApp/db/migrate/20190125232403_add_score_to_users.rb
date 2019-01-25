class AddScoreToUsers < ActiveRecord::Migration[5.2]
  def change
    add_column :users, :score, :integer, null: false, default: 0
  end
end

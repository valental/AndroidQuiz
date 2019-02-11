class CreateTokens < ActiveRecord::Migration[5.2]
  def change
    create_table :tokens do |t|
      t.belongs_to :user, index: true
      t.string :token, null: false

      t.timestamps null: false
    end
  end
end

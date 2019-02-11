# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 2019_02_11_100008) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "tokens", force: :cascade do |t|
    t.bigint "user_id"
    t.string "token", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_tokens_on_user_id"
  end

  create_table "users", force: :cascade do |t|
    t.string "email", null: false
    t.string "first_name"
    t.string "last_name"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.string "password_digest", default: "", null: false
    t.string "token", default: "", null: false
    t.string "registration_token"
    t.boolean "has_registered", default: false
    t.string "username", null: false
    t.integer "score", default: 0, null: false
    t.integer "geography", default: 1, null: false
    t.integer "sport", default: 1, null: false
    t.integer "movie", default: 1, null: false
    t.integer "history_art", default: 1, null: false
    t.integer "science", default: 1, null: false
    t.decimal "sport_1"
    t.decimal "sport_2"
    t.decimal "sport_3"
    t.decimal "history_art_1"
    t.decimal "history_art_2"
    t.decimal "history_art_3"
    t.decimal "geography_1"
    t.decimal "geography_2"
    t.decimal "geography_3"
    t.decimal "movie_1"
    t.decimal "movie_2"
    t.decimal "movie_3"
    t.decimal "science_1"
    t.decimal "science_2"
    t.decimal "science_3"
    t.index ["email"], name: "index_users_on_email", unique: true
    t.index ["token"], name: "index_users_on_token", unique: true
  end

end

class User < ApplicationRecord
  has_secure_password
  has_secure_token

  has_many :tokens, dependent: :destroy

  validates :email, presence: true,
                    uniqueness: { case_sensitive: false },
                    format: /\A[^@\s]+@([^@\s]+\.)+[^@\s]+\z/

  validates :username, presence: true,
                       uniqueness: { case_sensitive: true }

  scope :query, lambda { |query|
    where('(first_name || last_name || email) ILIKE ?', "%#{query}%")
  }

  def self.best_all
    User.order('movie + sport + science + geography + history_art desc').
         order('COALESCE(movie_1, 0) + COALESCE(movie_2, 0) + COALESCE(movie_3, 0) +
                COALESCE(science_1, 0) + COALESCE(science_2, 0) + COALESCE(science_3, 0) +
                COALESCE(geography_1, 0) + COALESCE(geography_2, 0) + COALESCE(geography_3, 0) +
                COALESCE(history_art_1, 0) + COALESCE(history_art_2, 0) + COALESCE(history_art_3, 0) +
                COALESCE(sport_1, 0) + COALESCE(sport_2, 0) + COALESCE(sport_3, 0) asc')
  end

  def self.best(category)
    User.order("#{category} desc").
         order("COALESCE(#{category}_1, 0) + COALESCE(#{category}_2, 0) + COALESCE(#{category}_3, 0) asc").
         where("#{category} > 1").limit(3)
  end

  def self.best_usernames(category)
    pl_1, pl_2, pl_3 = self.best(category)

    username_1 = pl_1 ? pl_1.username : '-'
    username_2 = pl_2 ? pl_2.username : '-'
    username_3 = pl_3 ? pl_3.username : '-'

    [username_1, username_2, username_3]
  end

  def progress
    (lvl_score * 100)/15
  end

  def lvl_score
    (movie + science + sport + geography + history_art) - 5
  end

  def time
    movie_time + science_time + geography_time + history_art_time + sport_time
  end

  def movie_time
    movie_1.to_f + movie_2.to_f + movie_3.to_f
  end

  def science_time
    science_1.to_f + science_2.to_f + science_3.to_f
  end

  def geography_time
    geography_1.to_f + geography_2.to_f + geography_3.to_f
  end

  def history_art_time
    history_art_1.to_f + history_art_2.to_f + history_art_3.to_f
  end

  def sport_time
    sport_1.to_f + sport_2.to_f + sport_3.to_f
  end

  def to_s
    "id: #{id}, username: #{username}, lvl_score: #{lvl_score}, time: #{time}"
  end
end

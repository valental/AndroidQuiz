package hr.math.quiz.api;


import hr.math.quiz.api.models.LeaderBoard;
import hr.math.quiz.api.models.Session;
import hr.math.quiz.api.models.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {
    static final String SERVER_URL = "https://androidquiz.herokuapp.com";

    static Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(SERVER_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

    static final ApiService service = retrofit.create(ApiService.class);

    public static Call<User> createUser(User user) {
        Call<User> createCall = service.create(user);

        return createCall;
    }

    public static Call<Session> startSession(Session session) {
        Call<Session> sessionCall = service.create(session);

        return sessionCall;
    }

    public static Call<LeaderBoard> getScores() {
        Call<LeaderBoard> leaderBoardCall = service.getScores();

        return leaderBoardCall;
    }
}

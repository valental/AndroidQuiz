package hr.math.quiz.api;

import hr.math.quiz.api.models.LeaderBoard;
import hr.math.quiz.api.models.Result;
import hr.math.quiz.api.models.Session;
import hr.math.quiz.api.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/users")
    Call<User> create(@Body User user);

    @POST("api/session")
    Call<Session> create(@Body Session session);

    @GET("api/scores")
    Call<LeaderBoard> getScores();

    @POST("api/level")
    Call<ResponseBody> upgradeLevel(@Body Result result, @Header("Authorization") String Authorization);

    @DELETE("api/session")
    Call<ResponseBody> logoutUser(@Header("Authorization") String token);
}

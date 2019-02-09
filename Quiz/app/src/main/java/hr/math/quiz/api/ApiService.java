package hr.math.quiz.api;

import hr.math.quiz.api.models.Session;
import hr.math.quiz.api.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/users")
    Call<User> create(@Body User user);

    @POST("api/session")
    Call<Session> create(@Body Session session);
}

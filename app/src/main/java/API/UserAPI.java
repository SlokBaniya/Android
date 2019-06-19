package API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("users/signup")
    Call<Void> addHero(@Body Users users);
}

package API;

import model.Users;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("users/signup")
    Call<Void> addUsers(@Body Users users);
}

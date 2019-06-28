package API;

import model.LoginSignupResponse;
import model.Users;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("api/user/register")
    Call<Void> addUsers(@Body Users users);

    @POST("api/user/login")
    Call<LoginSignupResponse> check(@Body Users users);
}

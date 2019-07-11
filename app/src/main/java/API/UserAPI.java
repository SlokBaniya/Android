package API;

import java.util.List;

import model.Items;
import model.LoginSignupResponse;
import model.Users;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPI {

    @POST("api/user/register")
    Call<Void> addUsers(@Body Users users);

    @POST("api/user/login")
    Call<LoginSignupResponse> check(@Body Users users);

    @GET("api/items/view")
    Call<List<Items>> getAllItems();

    @PUT("api/users/profile/update/{username}")
    Call<Void> updateUser(@Path("username") String username,@Body Users users);

    @GET("api/users/profile/{username}")
    Call<Users> getUserByUsername(@Path("username") String username);


}

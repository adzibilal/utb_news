package adapter;

import model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("cnn/terbaru")
    Call<ApiResponse> getBerita();
}
package com.example.utsmobileprograming1_adzibilalmh_22552011164;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.ApiInterface;
import adapter.BeritaAdapter;
import model.ApiResponse;
import model.Berita;
import model.Data;
import model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBerita;
    private BeritaAdapter beritaAdapter;
    private List<Berita> beritaList = new ArrayList<>();

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewBerita = findViewById(R.id.recyclerViewBerita);
        recyclerViewBerita.setLayoutManager(new LinearLayoutManager(this));
        beritaAdapter = new BeritaAdapter(this, beritaList);
        recyclerViewBerita.setAdapter(beritaAdapter);
        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-berita-indonesia.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the interface using the Retrofit object
        apiInterface = retrofit.create(ApiInterface.class);

        // Call the method to fetch data from the API
        getDataFromApi();

    }

    private void getDataFromApi() {
        // Lakukan permintaan HTTP ke API
        Call<ApiResponse> call = apiInterface.getBerita();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Proses respons dan tambahkan data berita ke dalam list
                    Data responseData = response.body().getData();
                    if (responseData != null) {
                        List<Post> posts = responseData.getPosts();
                        if (posts != null && !posts.isEmpty()) {
                            for (Post post : posts) {
                                // Membuat objek Berita dari setiap Post dalam respons
                                Berita berita = new Berita(
                                        post.getLink(),
                                        post.getTitle(),
                                        post.getPubDate(),
                                        post.getDescription(),
                                        post.getThumbnail()
                                );
                                // Menambahkan Berita ke dalam list
                                beritaList.add(berita);
                            }
                            // Beri tahu adapter bahwa dataset telah berubah
                            beritaAdapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    // Tangani jika permintaan tidak berhasil
                    // Misalnya, tampilkan pesan kesalahan
                    Toast.makeText(HomeActivity.this, "Gagal mendapatkan data berita", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
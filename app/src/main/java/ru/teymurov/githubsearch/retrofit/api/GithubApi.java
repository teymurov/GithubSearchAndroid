package ru.teymurov.githubsearch.retrofit.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import ru.teymurov.githubsearch.retrofit.gson.User;

public interface GithubApi {
    @GET("/user")
    Call<User> auth(@Header("Authorization") String token);
}
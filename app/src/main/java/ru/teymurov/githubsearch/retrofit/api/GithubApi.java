package ru.teymurov.githubsearch.retrofit.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import ru.teymurov.githubsearch.retrofit.gson.SearchResult;
import ru.teymurov.githubsearch.retrofit.gson.User;

public interface GithubApi {
    @GET("/user")
    Call<User> auth(@Header("Authorization") String token);

    @GET("/search/repositories?sort=stars&order=desc")
    Call<SearchResult> search(@Header("Authorization") String token, @Query("q") String query, @Query("page") int page, @Query("per_page") int pageSize);
}
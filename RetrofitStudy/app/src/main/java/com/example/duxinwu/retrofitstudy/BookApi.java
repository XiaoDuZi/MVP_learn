package com.example.duxinwu.retrofitstudy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public interface BookApi {
    @GET("/{book}")
    Call<Book> getBook(@Path("book") String path, @Query("price") String price);
}

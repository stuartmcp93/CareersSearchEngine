package com.stu.careers_search_engine;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceholderAPI {

    @GET("item/1")
    Call<List> getItem();

}
package com.stu.careers_search_engine;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceholderAPI {

    //Have to annotate method to tell retrofit what to do
    //@GET("posts")
    //Call<List<Question>> getQuestion();


    //@GET("questions")
    //Call<List<Question>> getQuestions();

    @GET("questions")
    Call<List<Question>> getQuestions();

    //https://stackoverflow.com/questions/38347121/get-list-from-json-using-retrofit-2-1




}
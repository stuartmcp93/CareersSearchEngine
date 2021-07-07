package com.stu.careers_search_engine;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceholderAPI {

    //Have to annotate method to tell retrofit what to do
    //@GET("posts")
    //Call<List<Question>> getQuestion();


    @GET("questions")
    Call<List<Question>> getQuestions();


}
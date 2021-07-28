package com.stu.careers_search_engine;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PlaceholderAPI {

    //Posts is the end point in the tutorial so will need to change this obvs
    /*@GET("posts")
    Call<List<JobSearchResult>> getJobs();*/



    /*@GET("search?keywords=accountant&location=london&employerid=123&distancefromlocation=15")
    Call<List<JobSearchResult>> getJobs();*/

    //@Headers({"Username: a58774e3-ec9a-4458-8e02-45e825f0224d"})
    //@Headers({"Authorization: Username a58774e3-ec9a-4458-8e02-45e825f0224d"})
    /*@Headers({
            "Authorization: Basic a58774e3-ec9a-4458-8e02-45e825f0224d:"
    })*/
    //@Headers("Username: a58774e3-ec9a-4458-8e02-45e825f0224d")
    /*@GET("search?keywords=java")
    Call<JobSearchResult> getJobs(@Header("Authorization") String authorization);*/

    @GET("search")
    Call<JobSearchResult> getJobs(@Header("Authorization") String authorization,
                                  @Query("keywords") String jobTitle,
                                  @Query("locationName") String location,
                                  @Query("minimumSalary") String salary,
                                  @Query("resultsToTake") int maxResults,
                                  @Query("distanceFromLocation") int distance);







}
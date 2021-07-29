package com.stu.careers_search_engine;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PlaceholderAPI {

    @GET("search")
    Call<JobSearchResult> getJobs(@Header("Authorization") String authorization,
                                  @Query("keywords") String jobTitle,
                                  @Query("locationName") String location,
                                  @Query("minimumSalary") String salary,
                                  @Query("resultsToTake") int maxResults,
                                  @Query("distanceFromLocation") int distance);
}






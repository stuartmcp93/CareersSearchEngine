package com.stu.careers_search_engine;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 *
 * This interface generates a GET HTTP request to search for job vacancies.
 *
 * @Author Stuart McPherson
 */

public interface PlaceholderAPI {

    /**
     * This method sends a the GET request to the API.
     *
     * @param authorization the authorization key needed to access the API
     * @param jobTitle the key word to add for the search
     * @param location the location to search for vacancies
     * @param salary the minimum salary to search for
     * @param maxResults the maximum number of results to return
     * @param distance the maximum distance (miles) from the location
     * @return a JSON array of search results
     */
    @GET("search")
    Call<JobSearchResult> getJobs(@Header("Authorization") String authorization,
                                  @Query("keywords") String jobTitle,
                                  @Query("locationName") String location,
                                  @Query("minimumSalary") String salary,
                                  @Query("resultsToTake") int maxResults,
                                  @Query("distanceFromLocation") int distance);
}






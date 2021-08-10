package com.stu.careers_search_engine;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * This class holds the JSON array containing a list of job search results
 * that is returned from the API search.
 *
 * @Author Stuart McPherson
 *
 */
@Generated("jsonschema2pojo")
public class JobSearchResult {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
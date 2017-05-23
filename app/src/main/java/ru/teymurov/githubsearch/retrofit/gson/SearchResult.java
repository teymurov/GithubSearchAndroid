package ru.teymurov.githubsearch.retrofit.gson;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("incomplete_results")
    private boolean mIncompleteResults;

    @SerializedName("items")
    private List<Repository> mRepositories;

    @SerializedName("total_count")
    private long mTotalCount;

    public boolean isIncompleteResults() {
        return mIncompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        mIncompleteResults = incompleteResults;
    }

    public List<Repository> getRepositories() {
        return mRepositories;
    }

    public void setRepositories(List<Repository> repositories) {
        mRepositories = repositories;
    }

    public long getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(long totalCount) {
        mTotalCount = totalCount;
    }
}

package ru.teymurov.githubsearch.retrofit.gson;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("created_at")
    private String mCreatedAt;

    @SerializedName("default_branch")
    private String mDefaultBranch;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("fork")
    private boolean mFork;

    @SerializedName("forks_count")
    private long mForksCount;

    @SerializedName("full_name")
    private String mFullName;

    @SerializedName("homepage")
    private String mHomepage;

    @SerializedName("html_url")
    private String mHtmlUrl;

    @SerializedName("id")
    private long mId;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("master_branch")
    private String mMasterBranch;

    @SerializedName("name")
    private String mName;

    @SerializedName("open_issues_count")
    private long mOpenIssuesCount;

    @SerializedName("owner")
    private Owner mOwner;

    @SerializedName("private")
    private boolean mPrivate;

    @SerializedName("pushed_at")
    private String mPushedAt;

    @SerializedName("score")
    private double mScore;

    @SerializedName("size")
    private long mSize;

    @SerializedName("stargazers_count")
    private long mStargazersCount;

    @SerializedName("updated_at")
    private String mUpdatedAt;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("watchers_count")
    private long mWatchersCount;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDefaultBranch() {
        return mDefaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        mDefaultBranch = defaultBranch;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isFork() {
        return mFork;
    }

    public void setFork(boolean fork) {
        mFork = fork;
    }

    public long getForksCount() {
        return mForksCount;
    }

    public void setForksCount(long forksCount) {
        mForksCount = forksCount;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        mHtmlUrl = htmlUrl;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getMasterBranch() {
        return mMasterBranch;
    }

    public void setMasterBranch(String masterBranch) {
        mMasterBranch = masterBranch;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getOpenIssuesCount() {
        return mOpenIssuesCount;
    }

    public void setOpenIssuesCount(long openIssuesCount) {
        mOpenIssuesCount = openIssuesCount;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public void setOwner(Owner owner) {
        mOwner = owner;
    }

    public boolean isPrivate() {
        return mPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        mPrivate = aPrivate;
    }

    public String getPushedAt() {
        return mPushedAt;
    }

    public void setPushedAt(String pushedAt) {
        mPushedAt = pushedAt;
    }

    public double getScore() {
        return mScore;
    }

    public void setScore(double score) {
        mScore = score;
    }

    public long getSize() {
        return mSize;
    }

    public void setSize(long size) {
        mSize = size;
    }

    public long getStargazersCount() {
        return mStargazersCount;
    }

    public void setStargazersCount(long stargazersCount) {
        mStargazersCount = stargazersCount;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public long getWatchersCount() {
        return mWatchersCount;
    }

    public void setWatchersCount(long watchersCount) {
        mWatchersCount = watchersCount;
    }
}

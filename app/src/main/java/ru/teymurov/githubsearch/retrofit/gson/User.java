package ru.teymurov.githubsearch.retrofit.gson;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    @SerializedName("bio")
    private Object mBio;

    @SerializedName("blog")
    private String mBlog;

    @SerializedName("collaborators")
    private long mCollaborators;

    @SerializedName("company")
    private Object mCompany;

    @SerializedName("created_at")
    private String mCreatedAt;

    @SerializedName("disk_usage")
    private long mDiskUsage;

    @SerializedName("email")
    private Object mEmail;

    @SerializedName("events_url")
    private String mEventsUrl;

    @SerializedName("followers")
    private long mFollowers;

    @SerializedName("followers_url")
    private String mFollowersUrl;

    @SerializedName("following")
    private long mFollowing;

    @SerializedName("following_url")
    private String mFollowingUrl;

    @SerializedName("gists_url")
    private String mGistsUrl;

    @SerializedName("gravatar_id")
    private String mGravatarId;

    @SerializedName("hireable")
    private Object mHireable;

    @SerializedName("html_url")
    private String mHtmlUrl;

    @SerializedName("id")
    private long mId;

    @SerializedName("location")
    private Object mLocation;

    @SerializedName("login")
    private String mLogin;

    @SerializedName("name")
    private Object mName;

    @SerializedName("organizations_url")
    private String mOrganizationsUrl;

    @SerializedName("owned_private_repos")
    private long mOwnedPrivateRepos;

    @SerializedName("plan")
    private Plan mPlan;

    @SerializedName("private_gists")
    private long mPrivateGists;

    @SerializedName("public_gists")
    private long mPublicGists;

    @SerializedName("public_repos")
    private long mPublicRepos;

    @SerializedName("received_events_url")
    private String mReceivedEventsUrl;

    @SerializedName("repos_url")
    private String mReposUrl;

    @SerializedName("site_admin")
    private boolean mSiteAdmin;

    @SerializedName("starred_url")
    private String mStarredUrl;

    @SerializedName("subscriptions_url")
    private String mSubscriptionsUrl;

    @SerializedName("total_private_repos")
    private long mTotalPrivateRepos;

    @SerializedName("two_factor_authentication")
    private boolean mTwoFactorAuthentication;

    @SerializedName("type")
    private String mType;

    @SerializedName("updated_at")
    private String mUpdatedAt;

    @SerializedName("url")
    private String mUrl;

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public Object getBio() {
        return mBio;
    }

    public void setBio(Object bio) {
        mBio = bio;
    }

    public String getBlog() {
        return mBlog;
    }

    public void setBlog(String blog) {
        mBlog = blog;
    }

    public long getCollaborators() {
        return mCollaborators;
    }

    public void setCollaborators(long collaborators) {
        mCollaborators = collaborators;
    }

    public Object getCompany() {
        return mCompany;
    }

    public void setCompany(Object company) {
        mCompany = company;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public long getDiskUsage() {
        return mDiskUsage;
    }

    public void setDiskUsage(long diskUsage) {
        mDiskUsage = diskUsage;
    }

    public Object getEmail() {
        return mEmail;
    }

    public void setEmail(Object email) {
        mEmail = email;
    }

    public String getEventsUrl() {
        return mEventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        mEventsUrl = eventsUrl;
    }

    public long getFollowers() {
        return mFollowers;
    }

    public void setFollowers(long followers) {
        mFollowers = followers;
    }

    public String getFollowersUrl() {
        return mFollowersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        mFollowersUrl = followersUrl;
    }

    public long getFollowing() {
        return mFollowing;
    }

    public void setFollowing(long following) {
        mFollowing = following;
    }

    public String getFollowingUrl() {
        return mFollowingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        mFollowingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return mGistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        mGistsUrl = gistsUrl;
    }

    public String getGravatarId() {
        return mGravatarId;
    }

    public void setGravatarId(String gravatarId) {
        mGravatarId = gravatarId;
    }

    public Object getHireable() {
        return mHireable;
    }

    public void setHireable(Object hireable) {
        mHireable = hireable;
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

    public Object getLocation() {
        return mLocation;
    }

    public void setLocation(Object location) {
        mLocation = location;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public Object getName() {
        return mName;
    }

    public void setName(Object name) {
        mName = name;
    }

    public String getOrganizationsUrl() {
        return mOrganizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        mOrganizationsUrl = organizationsUrl;
    }

    public long getOwnedPrivateRepos() {
        return mOwnedPrivateRepos;
    }

    public void setOwnedPrivateRepos(long ownedPrivateRepos) {
        mOwnedPrivateRepos = ownedPrivateRepos;
    }

    public Plan getPlan() {
        return mPlan;
    }

    public void setPlan(Plan plan) {
        mPlan = plan;
    }

    public long getPrivateGists() {
        return mPrivateGists;
    }

    public void setPrivateGists(long privateGists) {
        mPrivateGists = privateGists;
    }

    public long getPublicGists() {
        return mPublicGists;
    }

    public void setPublicGists(long publicGists) {
        mPublicGists = publicGists;
    }

    public long getPublicRepos() {
        return mPublicRepos;
    }

    public void setPublicRepos(long publicRepos) {
        mPublicRepos = publicRepos;
    }

    public String getReceivedEventsUrl() {
        return mReceivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        mReceivedEventsUrl = receivedEventsUrl;
    }

    public String getReposUrl() {
        return mReposUrl;
    }

    public void setReposUrl(String reposUrl) {
        mReposUrl = reposUrl;
    }

    public boolean isSiteAdmin() {
        return mSiteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        mSiteAdmin = siteAdmin;
    }

    public String getStarredUrl() {
        return mStarredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        mStarredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return mSubscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        mSubscriptionsUrl = subscriptionsUrl;
    }

    public long getTotalPrivateRepos() {
        return mTotalPrivateRepos;
    }

    public void setTotalPrivateRepos(long totalPrivateRepos) {
        mTotalPrivateRepos = totalPrivateRepos;
    }

    public boolean isTwoFactorAuthentication() {
        return mTwoFactorAuthentication;
    }

    public void setTwoFactorAuthentication(boolean twoFactorAuthentication) {
        mTwoFactorAuthentication = twoFactorAuthentication;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
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
}
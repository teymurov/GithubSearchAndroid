package ru.teymurov.githubsearch.retrofit.gson;

import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    @SerializedName("gravatar_id")
    private String mGravatarId;

    @SerializedName("id")
    private long mId;

    @SerializedName("login")
    private String mLogin;

    @SerializedName("received_events_url")
    private String mReceivedEventsUrl;

    @SerializedName("type")
    private String mType;

    @SerializedName("url")
    private String mUrl;

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getGravatarId() {
        return mGravatarId;
    }

    public void setGravatarId(String gravatarId) {
        mGravatarId = gravatarId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getReceivedEventsUrl() {
        return mReceivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        mReceivedEventsUrl = receivedEventsUrl;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
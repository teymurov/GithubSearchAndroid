package ru.teymurov.githubsearch.retrofit.gson;

import com.google.gson.annotations.SerializedName;

public class Plan {

    @SerializedName("collaborators")
    private long mCollaborators;

    @SerializedName("name")
    private String mName;

    @SerializedName("private_repos")
    private long mPrivateRepos;

    @SerializedName("space")
    private long mSpace;

    public long getCollaborators() {
        return mCollaborators;
    }

    public void setCollaborators(long collaborators) {
        mCollaborators = collaborators;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getPrivateRepos() {
        return mPrivateRepos;
    }

    public void setPrivateRepos(long privateRepos) {
        mPrivateRepos = privateRepos;
    }

    public long getSpace() {
        return mSpace;
    }

    public void setSpace(long space) {
        mSpace = space;
    }
}
package ru.teymurov.githubsearch.retrofit.errors;

import com.google.gson.annotations.SerializedName;

public class ErrorBody {

    @SerializedName("resource")
    private String mResource;

    @SerializedName("field")
    private String mField;

    @SerializedName("code")
    private String mCode;

    public String getResource() {
        return mResource;
    }

    public void setResource(String resource) {
        mResource = resource;
    }

    public String getField() {
        return mField;
    }

    public void setField(String field) {
        mField = field;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }
}

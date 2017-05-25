package ru.teymurov.githubsearch.retrofit.errors;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiError {

    @SerializedName("message")
    private String mMessage;

    @SerializedName("errors")
    private List<ErrorBody> mErrors;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<ErrorBody> getErrors() {
        return mErrors;
    }

    public void setErrors(List<ErrorBody> errors) {
        mErrors = errors;
    }
}

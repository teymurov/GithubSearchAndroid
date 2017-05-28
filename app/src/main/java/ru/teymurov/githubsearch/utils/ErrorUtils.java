package ru.teymurov.githubsearch.utils;

import android.content.Context;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import ru.teymurov.githubsearch.R;
import ru.teymurov.githubsearch.dagger.DaggerCore;
import ru.teymurov.githubsearch.retrofit.errors.ApiError;

public class ErrorUtils {

    private Context mContext;

    public ErrorUtils(Context context) {
        mContext = context;
    }

    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                DaggerCore.getAppComponent().getRetrofit()
                        .responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError apiError;
        try {
            apiError = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return apiError;
    }

    public String getErrorMessageFromCode(int code) {
        if (code == 401) {
            return mContext.getString(R.string.error_message_unauthenticated);
        } else if (code == 403) {
            return mContext.getString(R.string.error_message_rate_limit);
        } else if (code == 400 || code == 422) {
            return mContext.getString(R.string.error_message_query);
        }

        return null;
    }

    public String getErrorMessageFromThrowable(Throwable t) {
        if (t instanceof IOException) {
            return mContext.getString(R.string.error_message_network);
        }

        return null;
    }
}

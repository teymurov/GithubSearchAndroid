package ru.teymurov.githubsearch.dagger.application.modules;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.teymurov.githubsearch.BuildConfig;
import ru.teymurov.githubsearch.R;
import ru.teymurov.githubsearch.retrofit.api.GithubApi;

@Module
public class RetrofitModule {

	@Provides
	@Singleton
	String providerGithubUrl(final Context context) {
		return context.getString(R.string.github_api_url);
	}

	@Provides
	@Singleton
	GithubApi provideGithubApi(Retrofit retrofit) {
		return retrofit.create(GithubApi.class);
	}

	@Provides
	@Singleton
    Retrofit provideGithubRetrofit(Retrofit.Builder builder, final String GithubUrl) {
		return builder.baseUrl(GithubUrl).build();
	}

	@Provides
	@Singleton
	Retrofit.Builder provideRetrofitBuilder(Converter.Factory converterFactory) {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

		return new Retrofit.Builder()
				.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
				.addConverterFactory(converterFactory)
				.client(client);
	}

	@Provides
	@Singleton
	Converter.Factory provideConverterFactory(Gson gson) {
		return GsonConverterFactory.create(gson);
	}

	@Provides
	@Singleton
	Gson provideGson() {
		return new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.setFieldNamingStrategy(new CustomFieldNamingPolicy())
				.setPrettyPrinting()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
				.serializeNulls()
				.create();
	}

	private static class CustomFieldNamingPolicy implements FieldNamingStrategy {
		@Override
		public String translateName(Field field) {
			String name = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field);
			name = name.substring(2, name.length()).toLowerCase();
			return name;
		}
	}
}
package com.example.baseassignmentapp.modules

import android.app.Application
import com.example.baseassignmentapp.home.HomeRepo
import com.example.baseassignmentapp.home.HomeRepoImp
import com.example.baseassignmentapp.retrofit.SearchApi
import com.example.baseassignmentapp.viewModel.HomeViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {

    fun provideHomeViewModel(
        apis: HomeRepo,
        application: Application
    ): HomeViewModel {
        return HomeViewModel(apis, application)
    }

    single { provideHomeViewModel(get(), get()) }
    single<HomeRepo> { HomeRepoImp(get()) }
}

val apiModule = module {
    fun provideRepoApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    single { provideRepoApi(get()) }
}

val retrofitModule = module {

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        okHttpClientBuilder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Content-Type", "application/json")
            chain.proceed(request.build())
        }
        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

}
package com.example.android_assignment2.di

import com.example.android_assignment2.rest.MusicApi
import com.example.android_assignment2.rest.MusicRetrofit

import dagger.Module
import dagger.Provides

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

import javax.inject.Singleton

//This class will provide all objects for networking interface
@Module
class NetworkModule {

    //This method provides the logging interceptor needed in the provideOkHttpCLient()
    //The singleton will provide only one instance and called any time its needed
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //This method will provide the okHttp client needed in the provideNetworkApi method
    //Uses a logging interceptor from Dagger and will be called everytime we need it
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    //This method provide the network api to be injected into other classes by Dagger
    //Uses the http client from Dagger for the Retrofit object and will be called
    //everytime we need it
    @Provides
    @Singleton
    fun provideNetworkApi(okHttpClient: OkHttpClient): MusicApi =
        Retrofit.Builder()
            .baseUrl(MusicApi.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MusicApi::class.java)
}
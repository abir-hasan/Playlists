package com.abir.hasan.androidtdd.ui.playlist.di

import com.abir.hasan.androidtdd.data.remote.PlaylistAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class PlaylistModule {

    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        //.baseUrl("http://127.0.0.1:3000/") // Check local IP
        .baseUrl("http://10.0.2.2:3000/") // Check local IP
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun playlistAPI(retrofit: Retrofit): PlaylistAPI {
        return retrofit.create(PlaylistAPI::class.java)
    }


}
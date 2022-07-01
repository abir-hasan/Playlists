package com.abir.hasan.androidtdd.ui.playlist.di

import androidx.test.espresso.IdlingResource
import com.abir.hasan.androidtdd.data.remote.PlaylistAPI
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val client = OkHttpClient()
val idlingResource: IdlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        //.baseUrl("http://127.0.0.1:3000/") // Check local IP
        .baseUrl("http://10.0.2.2:3000/") // Check local IP
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    fun playlistAPI(retrofit: Retrofit): PlaylistAPI {
        return retrofit.create(PlaylistAPI::class.java)
    }


}
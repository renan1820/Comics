package com.example.comics.renan.di

import com.example.comics.renan.data.remote.ComicsApi
import com.example.comics.renan.data.remote.ComicsApi.Companion.BASE_URL
import com.example.comics.renan.data.repository.ComicsRepositoryImpl
import com.example.comics.renan.domain.repository.ComicsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideComicsApi(): ComicsApi {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ComicsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideComicsRepository(comicsApi: ComicsApi): ComicsRepository{
        return ComicsRepositoryImpl(comicsApi)
    }
}
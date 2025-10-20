package com.pranish.s8069126assignment2.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import com.pranish.s8069126assignment2.network.NitApi
import com.pranish.s8069126assignment2.data.Repo
import java.time.Duration

private const val BASE_URL = "https://nit3213api.onrender.com/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides @Singleton fun moshi(): Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides @Singleton fun okHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .callTimeout(Duration.ofSeconds(30))
            .connectTimeout(Duration.ofSeconds(15))
            .readTimeout(Duration.ofSeconds(30))
            .writeTimeout(Duration.ofSeconds(30))
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

    @Provides @Singleton fun retrofit(m: Moshi, ok: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(m))
            .client(ok)
            .build()

    @Provides @Singleton fun api(r: Retrofit): NitApi = r.create(NitApi::class.java)
    @Provides @Singleton fun repo(a: NitApi): Repo = Repo(a)
}

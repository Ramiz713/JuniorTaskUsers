package com.task.users.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.task.users.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .client(buildClient())
        .baseUrl(BuildConfig.API_BASE_URL)
        .build()

    private fun buildClient(): OkHttpClient =
        OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
//            .addInterceptor {
//                val request = it.request()
//                    .newBuilder()
//                    .addHeader("X-Device-UDID", udid)
//                    .build()
//                it.proceed(request)
//            }
            .build()
}

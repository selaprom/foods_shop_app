package kh.com.sela.developer.basic.sela_food_app.di

import kh.com.sela.developer.basic.sela_food_app.data.remote.api.ProductApiService


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kh.com.sela.developer.basic.sela_food_app.data.remote.api.AuthApiService
import kh.com.sela.developer.basic.sela_food_app.data.remote.api.FavoriteApiService

import retrofit2.Retrofit
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {



    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApiService =
        retrofit.create(ProductApiService::class.java)

    @Provides
    @Singleton
    fun provideFavoriteApi(retrofit: Retrofit): FavoriteApiService =
        retrofit.create(FavoriteApiService::class.java)

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)



}
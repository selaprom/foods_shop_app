package kh.com.sela.developer.basic.sela_food_app.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kh.com.sela.developer.basic.sela_food_app.data.repository.AuthRepositoryImpl
import kh.com.sela.developer.basic.sela_food_app.data.repository.FavoriteRepositoryImpl
import kh.com.sela.developer.basic.sela_food_app.data.repository.ProductRepositoryImpl
import kh.com.sela.developer.basic.sela_food_app.domain.repository.AuthRepository
import kh.com.sela.developer.basic.sela_food_app.domain.repository.FavoriteRepository
import kh.com.sela.developer.basic.sela_food_app.domain.repository.ProductRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        impl: FavoriteRepositoryImpl
    ): FavoriteRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}


package com.tunahan.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tunahan.weatherapp.R
import com.tunahan.weatherapp.repo.WeatherRepository
import com.tunahan.weatherapp.repo.WeatherRepositoryInterface
import com.tunahan.weatherapp.roomdb.WeatherDao
import com.tunahan.weatherapp.roomdb.WeatherDatabase
import com.tunahan.weatherapp.service.WeatherAPI
import com.tunahan.weatherapp.util.Constans.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context,WeatherDatabase::class.java,"weatherDB").build()

    @Singleton
    @Provides
    fun injectDao(database:WeatherDatabase) = database.weatherDao()

    @Singleton
    @Provides
    fun injectNormalRepo(dao: WeatherDao,weatherAPI: WeatherAPI) = WeatherRepository(dao,weatherAPI) as WeatherRepositoryInterface


    @Singleton
    @Provides
    fun injectRetrofitAPI() : WeatherAPI{

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
}
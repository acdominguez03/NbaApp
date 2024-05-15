package com.example.nbaapp.di

import com.example.nbaapp.data.remote.NbaApi
import com.example.nbaapp.data.remote.RemoteDataSource
import com.example.nbaapp.data.repository.TeamRepository
import com.example.nbaapp.data.repository.TeamRepositoryImpl
import com.example.nbaapp.utils.Constants
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
    fun providesNbaApi(): NbaApi {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NbaApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(nbaApi: NbaApi): RemoteDataSource {
        return RemoteDataSource(nbaApi = nbaApi)
    }

    @Provides
    @Singleton
    fun providesTeamRepository(remoteDataSource: RemoteDataSource): TeamRepository {
        return TeamRepositoryImpl(remoteDataSource = remoteDataSource)
    }
}
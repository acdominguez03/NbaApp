package com.example.nbaapp.data.remote

import com.example.nbaapp.data.dto.TeamDetailDTO.TeamDetailDTO
import com.example.nbaapp.data.dto.TeamsDTO.TeamsDTO
import com.example.nbaapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NbaApi {

    @Headers("x-rapidapi-key: ${Constants.API_KEY}", "x-rapidapi-host: ${Constants.X_RAPID_API_HOST}")
    @GET("standings")
    suspend fun getAllNbaTeams(
        @Query("league") league: Int,
        @Query("season") season: String
    ): TeamsDTO

    @Headers("x-rapidapi-key: ${Constants.API_KEY}", "x-rapidapi-host: ${Constants.X_RAPID_API_HOST}")
    @GET("statistics")
    suspend fun getTeamDetail(
        @Query("league") league: Int,
        @Query("season") season: String,
        @Query("team") teamId: Int,
    ): TeamDetailDTO

}
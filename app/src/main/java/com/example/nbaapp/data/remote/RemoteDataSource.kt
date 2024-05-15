package com.example.nbaapp.data.remote

import com.example.nbaapp.domain.model.TeamDetailModel
import com.example.nbaapp.domain.model.TeamStandingModel
import com.example.nbaapp.utils.Constants
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val nbaApi: NbaApi
) {
    suspend fun getAllNbaTeams(): List<TeamStandingModel> {
        return nbaApi.getAllNbaTeams(league = Constants.LEAGUE, season = Constants.SEASON).response[0].map {
            it.toMap()
        }
    }

    suspend fun getTeamDetail(teamId: Int): TeamDetailModel {
        return nbaApi.getTeamDetail(league = Constants.LEAGUE, season = Constants.SEASON, teamId = teamId).response.toMap()
    }
}
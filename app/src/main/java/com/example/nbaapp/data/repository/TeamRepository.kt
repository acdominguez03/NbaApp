package com.example.nbaapp.data.repository

import com.example.nbaapp.data.Result
import com.example.nbaapp.data.remote.RemoteDataSource
import com.example.nbaapp.domain.model.TeamDetailModel
import com.example.nbaapp.domain.model.TeamStandingModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

interface TeamRepository {
    fun getAllNbaTeams(): Flow<Result<List<TeamStandingModel>>>
    fun getTeamDetail(teamId: Int): Flow<Result<TeamDetailModel>>
}

class TeamRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): TeamRepository {
    override fun getAllNbaTeams(): Flow<Result<List<TeamStandingModel>>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getAllNbaTeams()
            emit(Result.Success(response))
        } catch (e: IOException) {
            emit(
                Result.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            )
        } catch (e: HttpException) {
            emit(
                Result.Error(
                    message = "Couldn´t reach server, check your internet connection",
                    data = null
                )
            )
        }
    }

    override fun getTeamDetail(teamId: Int): Flow<Result<TeamDetailModel>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getTeamDetail(teamId)
            emit(Result.Success(response))
        } catch (e: IOException) {
            emit(
                Result.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            )
        } catch (e: HttpException) {
            emit(
                Result.Error(
                    message = "Couldn´t reach server, check your internet connection",
                    data = null
                )
            )
        }
    }

}
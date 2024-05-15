package com.example.nbaapp.domain.use_case

import com.example.nbaapp.data.Result
import com.example.nbaapp.data.repository.TeamRepository
import com.example.nbaapp.domain.model.TeamStandingModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTeamsUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    operator fun invoke(): Flow<Result<List<TeamStandingModel>>> {
        return repository.getAllNbaTeams()
    }
}
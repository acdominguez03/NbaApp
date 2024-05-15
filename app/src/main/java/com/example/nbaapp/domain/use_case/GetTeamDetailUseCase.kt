package com.example.nbaapp.domain.use_case

import com.example.nbaapp.data.Result
import com.example.nbaapp.data.repository.TeamRepository
import com.example.nbaapp.domain.model.TeamDetailModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamDetailUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    operator fun invoke(teamId: Int): Flow<Result<TeamDetailModel>> {
        return repository.getTeamDetail(teamId)
    }
}
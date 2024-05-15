package com.example.nbaapp.data.dto.TeamDetailDTO

data class TeamDetailDTO(
    val errors: List<Any>,
    val get: String,
    val parameters: ParametersDetail,
    val response: ResponseDetail,
    val results: Int
)
package com.example.nbaapp.data.dto.TeamsDTO

data class TeamsDTO(
    val errors: List<Any>,
    val get: String,
    val parameters: Parameters,
    val response: List<List<Response>>,
    val results: Int
)
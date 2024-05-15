package com.example.nbaapp.domain.model

data class TeamStandingModel(
    val teamId: Int,
    val teamName: String,
    val teamLogo: String,
    val wins: Int,
    val loses: Int,
    val conference: String,
    val position: Int
)

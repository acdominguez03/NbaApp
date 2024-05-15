package com.example.nbaapp.data.dto.TeamsDTO

import com.google.gson.annotations.SerializedName

data class Points(
    val against: Int,
    @SerializedName("for") val pointsFor: Int
)
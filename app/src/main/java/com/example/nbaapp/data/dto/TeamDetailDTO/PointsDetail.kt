package com.example.nbaapp.data.dto.TeamDetailDTO

import com.google.gson.annotations.SerializedName

data class PointsDetail(
    val against: Against,
    @SerializedName("for") val forPoints: For
)
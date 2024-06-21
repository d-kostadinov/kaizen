package com.kaizeninterview.domain.cmn

import com.google.gson.annotations.SerializedName

data class Event(

    @SerializedName("i")
    val id: String,
    @SerializedName("d")
    val description: String,
    @SerializedName("sh")
    val shortDescription: String,
    @SerializedName("tt")
    val startTime: Long
)

data class Sport(
    @SerializedName("i")
    val id: String,
    @SerializedName("d")
    val description: String,
    @SerializedName("e")
    val events: List<Event>
)

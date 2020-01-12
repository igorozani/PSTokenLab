package com.example.pstokenlab

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Filme (
    val title: String?,
    val poster_url: String?,
    val vote_average: String?,
    val release_date: String?): Serializable
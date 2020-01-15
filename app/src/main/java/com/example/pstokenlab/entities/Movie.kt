package com.example.pstokenlab.entities

import java.io.Serializable

data class Movie (
    val backdrop_url: String?,
    val overview: String?,
    val release_date: String?,
    val vote_average: Float?,
    val title: String?,
    val genres: List<String>?,
    val runtime: String?,
    val id: String): Serializable
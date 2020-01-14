package com.example.pstokenlab

import java.io.Serializable

data class Filme (
    val backdrop_url: String?,
    val overview: String?,
    val release_date: String?,
    val vote_average: Float?,
    val title: String?,
    val genres: List<String>?,
    val runtime: String?): Serializable
package com.starwars.flow.dashboard.model

data class GetTypesResponse(
    val films: String,
    val people: String,
    val planets: String,
    val species: String,
    val starships: String,
    val vehicles: String
)
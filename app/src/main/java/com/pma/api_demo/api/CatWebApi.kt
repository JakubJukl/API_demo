package com.pma.api_demo.api

import retrofit2.http.Body
import retrofit2.http.GET

interface CatWebApi {
    @GET("fact")
    suspend fun getCatFact(@Body apiRequest: CatRequest): CatFact

    @GET("fact")
    suspend fun getCatFact(): CatFact

    @GET("breeds")
    suspend fun getCatBreeds(@Body apiRequest: CatRequest): BreedList

    @GET("breeds")
    suspend fun getCatBreeds(): BreedList
}

package com.pma.api_demo.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://catfact.ninja/"

class CatWebService(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val api: CatWebApi by lazy {
        createCatWebApi()
    }

    private fun createCatWebApi(): CatWebApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(CatWebApi::class.java)
    }

    suspend fun getCatFact(length: Int? = null): CatFact {
        return withContext(dispatcher) {
            if (length == null) {
                api.getCatFact()
            } else {
                api.getCatFact(CatRequest(length, null))
            }
        }
    }

    suspend fun getCatBreeds(limit: Int? = null): BreedList {
        return withContext(dispatcher) {
            if (limit == null) {
                api.getCatBreeds()
            } else {
                api.getCatBreeds(CatRequest(null, limit))
            }
        }
    }

}

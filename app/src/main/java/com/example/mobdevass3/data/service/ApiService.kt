package com.example.mobdevass3.data.service


import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("v2/oauth2/token")
    fun getAuthToken(
        @Field("grant_type") clientCredentials: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Single<TokenResponse>

    @GET("v2/types")
    fun getAnimalTypes(): Observable<AnimalTypesResponse>

    @GET("v2/types/{type}/breeds")
    fun getBreeds(@Path("type") animalTypeName: String): Observable<BreedsResponse>

    @GET("v2/animals")
    fun getAnimals(@Query("type") type: String, @Query("breed") breed: String) : Observable<AnimalsResponse>

    @GET("v2/animals")
    fun getAnimals() : Observable<AnimalsResponse>
}
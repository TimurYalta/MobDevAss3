package com.example.mobdevass3.data.service

import com.example.mobdevass3.domain.entity.Animal
import com.example.mobdevass3.domain.entity.AnimalType
import com.example.mobdevass3.domain.entity.Breed
import com.google.gson.annotations.SerializedName

data class AnimalsResponse(val animals: List<Animal>)

data class AnimalTypesResponse(val types: ArrayList<AnimalType>)

data class BreedsResponse(val breeds: ArrayList<Breed>)

data class TokenResponse(
    @SerializedName("token_type")
    private val tokenType: String,
    @SerializedName("expires_in")
    private val expiresIn: Long,
    @SerializedName("access_token") val accessToken: String
)
package com.example.mobdevass3.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class AnimalType(
    @PrimaryKey
    val name: String,
    val coats: ArrayList<String>,
    val colors: ArrayList<String>,
    val genders: ArrayList<String>
)
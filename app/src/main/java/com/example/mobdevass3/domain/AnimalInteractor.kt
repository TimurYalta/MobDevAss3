package com.example.mobdevass3.domain

import com.example.mobdevass3.data.AnimalRepository

class AnimalInteractor (private val animalRepo: AnimalRepository){
    fun getTypes() = animalRepo.getTypes()
    fun getBreeds(animalType: String) = animalRepo.getBreeds(animalType)
    fun getAnimals(animalType:String, animalBreed: String) = animalRepo.getAnimals(animalType,animalBreed)
    fun getAnimals() = animalRepo.getAnimals()
}
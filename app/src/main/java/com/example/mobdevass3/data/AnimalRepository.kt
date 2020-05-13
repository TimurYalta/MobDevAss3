package com.example.mobdevass3.data

import com.example.mobdevass3.data.localDB.AnimalDatabase
import com.example.mobdevass3.data.service.ApiService
import com.example.mobdevass3.domain.entity.Animal
import com.example.mobdevass3.domain.entity.AnimalType
import com.example.mobdevass3.domain.entity.Breed
import com.example.mobdevass3.utils.isInternetAvailable
import com.example.mobdevass3.utils.isNetworkConnected
import com.github.ajalt.timberkt.Timber
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class AnimalRepository(
    private val api: ApiService,
    db: AnimalDatabase
) {
    private val compositeDisposable = CompositeDisposable()
    private val animalTypeDao = db.animalTypeDao()
    private val animalDao = db.animalDao()
    private val breedDAO = db.breedDao()
    fun getTypes(): Single<List<AnimalType>> {
        if (isInternetAvailable()) {
            api.getAnimalTypes()
                .subscribeOn(Schedulers.io())
                .doOnError {  Timber.d{"Error while getting types: ${it.message}"} }
                .subscribe {
                    it.types.forEach { animalType ->
                        animalTypeDao.insertAnimalType(animalType)
                    }
                }.addTo(compositeDisposable)
        }
        return animalTypeDao.getAll()
    }

    fun getBreeds(animalTypeName: String): Single<List<Breed>> {
        if (isInternetAvailable()) {
            api.getBreeds(animalTypeName)
                .subscribeOn(Schedulers.io())
                .doOnError {  Timber.d{"Error with getting breeds for: $animalTypeName error message: ${it.message}"} }
                .subscribe {
                    it.breeds.forEach {breed ->
                        breed.type = animalTypeName
                        breedDAO.insertBreed(breed)
                    }
                }
                .addTo(compositeDisposable)
        }
        return breedDAO.getBreedsWithType(animalTypeName)
    }


    fun getAnimals(type: String, breed: String) : Single<List<Animal>> {
        if(isInternetAvailable()){
            api.getAnimals(type, breed)
                .subscribeOn(Schedulers.io())
                .doOnError {  Timber.d{"Error while getting animals with $type and breed: $breed error message: ${it.message}" }}
                .subscribe {
                    it.animals.forEach {  animal->
                        animal.breed = breed
                        animal.type = type
                        animalDao.insertAnimal(animal)
                    }
                }
                .addTo(compositeDisposable)
        }
        return animalDao.getAllWithTypeAndBreed(type, breed)
    }

    fun getAnimals() : Single<List<Animal>> {
        if(isInternetAvailable()){
            api.getAnimals()
                .subscribeOn(Schedulers.io())
                .doOnError {  Timber.d{"Error while getting animals: ${it.message}" }}
                .subscribe {
                    it.animals.forEach {  animal->
                        animalDao.insertAnimal(animal)
                    }
                }
                .addTo(compositeDisposable)
        }
        return animalDao.getAll()
    }
}
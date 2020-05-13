package com.example.mobdevass3.presentation.mainview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobdevass3.data.prefs.Prefs
import com.example.mobdevass3.data.service.ApiService
import com.example.mobdevass3.data.service.TokenResponse
import com.example.mobdevass3.domain.AnimalInteractor
import com.example.mobdevass3.domain.entity.Animal
import com.example.mobdevass3.domain.entity.AnimalType
import com.example.mobdevass3.domain.entity.Breed
import com.github.ajalt.timberkt.Timber
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import com.example.mobdevass3.utils.Consts


class MainViewModel(private val animalInteractor: AnimalInteractor, private val api: ApiService) : ViewModel() {
    private val disposables = CompositeDisposable()

    val animalTypes = MutableLiveData<List<AnimalType>>()
    val breeds = MutableLiveData<List<Breed>>()
    val animals = MutableLiveData<List<Animal>>()
    val progressStatus = MutableLiveData<String>()
    lateinit var lastDetailedAnimal : Animal
    fun getAnimalTypes() {
        animalInteractor.getTypes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                animalTypes.value = it
                progressStatus.value = "hide"
            }
            .addTo(disposables)
    }

    fun getBreeds(name: String) {
        animalInteractor.getBreeds(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                breeds.value = it
            }
            .addTo(disposables)
    }

    fun getAnimals(type: String, breed: String) {
        animalInteractor.getAnimals(type, breed)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                animals.value = it
            }
            .addTo(disposables)
    }

    fun getAnimals() {
        animalInteractor.getAnimals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                animals.value = it
            }
            .addTo(disposables)
    }

    fun initToken() {
        api.getAuthToken("client_credentials", Consts.API_CLIENT_ID, Consts.API_SECRET_KEY)
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<TokenResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {
                    Timber.d { "TokenRequest:  error while getting token : $e" }
                    getAnimalTypes()
                }
                override fun onSuccess(value: TokenResponse) {
                    value.run {
                        Timber.d { "TokenRequest: Token was succesfully taken" }
                        Prefs.putToken(accessToken)
                        progressStatus.postValue("show")
                        getAnimalTypes()
                    }
                }

            })
    }

}
package com.example.mobdevass3.data.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobdevass3.domain.entity.Animal
import io.reactivex.Single

@Dao
interface AnimalDAO {

    @Query("SELECT * FROM Animal")
    fun getAll(): Single<List<Animal>>

    @Query("SELECT * FROM Animal WHERE type=:type and breed=:breed")
    fun getAllWithTypeAndBreed(type:String, breed: String): Single<List<Animal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnimal(animal: Animal): Long

}
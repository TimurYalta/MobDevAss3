package com.example.mobdevass3.data.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobdevass3.domain.entity.AnimalType
import io.reactivex.Single


@Dao
interface AnimalTypeDAO {


    @Query("SELECT * FROM AnimalType")
    fun getAll(): Single<List<AnimalType>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnimalType(animalType: AnimalType): Long

}
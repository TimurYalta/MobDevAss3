package com.example.mobdevass3.data.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobdevass3.domain.entity.Animal
import com.example.mobdevass3.domain.entity.AnimalType
import com.example.mobdevass3.domain.entity.Breed
import com.example.mobdevass3.utils.Converters

@Database(
    entities = [AnimalType::class, Breed::class, Animal::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AnimalDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AnimalDatabase? = null

        fun getInstance(context: Context): AnimalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalDatabase::class.java,
                    "animals_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun animalTypeDao(): AnimalTypeDAO
    abstract fun breedDao() : BreedDAO
    abstract fun animalDao(): AnimalDAO
}

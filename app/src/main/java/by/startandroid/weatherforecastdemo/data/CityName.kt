package by.startandroid.weatherforecastdemo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_name")
data class CityName (
        @PrimaryKey var name: String
        )
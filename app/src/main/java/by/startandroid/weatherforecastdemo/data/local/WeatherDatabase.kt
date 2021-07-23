package by.startandroid.weatherforecastdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.Weather
import by.startandroid.weatherforecastdemo.data.WeatherForecast

@Database(entities = [CityName::class, WeatherForecast::class, Weather::class], exportSchema = false, version = 2)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
package by.startandroid.weatherforecastdemo.data.local

import android.content.Context
import androidx.room.Room
import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.WeatherForecast

class Local (context: Context) {

    private val database: WeatherDatabase = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java, "weather_db"
    ).build()

    suspend fun insertCityName(cityName: CityName) {
        database.weatherDao().insertCityName(cityName)
    }

    suspend fun getAllName(): MutableList<CityName> {
        return database.weatherDao().getAllName()
    }

    suspend fun getCityName(name: String): MutableList<CityName>{
        return database.weatherDao().getCityName(name)
    }

    suspend fun getNameList(): MutableList<String> {
        return database.weatherDao().getNameList()
    }

    suspend fun deleteAllCityName() {
        database.weatherDao().deleteAllCityName()
    }

    suspend fun deleteOneCityName(name: CityName) {
        database.weatherDao().deleteOneCityName(name)
    }

    suspend fun insertWeatherForecast(weatherForecast: WeatherForecast) {
        database.weatherDao().insertWeatherForecast(weatherForecast)
    }

    suspend fun getAllWeatherForecast(): MutableList<WeatherForecast> {
        return database.weatherDao().getAllWeatherForecast()
    }

    suspend fun getOneWeatherForecast(name: String): WeatherForecast {
        return database.weatherDao().getOneWeatherForecast(name)
    }

    suspend fun updateWeatherForecast(weatherForecast: WeatherForecast) {
        database.weatherDao().updateWeatherForecast(weatherForecast)
    }

    suspend fun updateAllWeatherForecast(weatherForecast: MutableList<WeatherForecast>) {
        database.weatherDao().updateAllWeatherForecast(weatherForecast)
    }

    suspend fun deleteAllWeatherForecast() {
        database.weatherDao().deleteAllWeatherForecast()
    }
}
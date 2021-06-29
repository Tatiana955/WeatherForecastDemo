package by.startandroid.weatherforecastdemo.repository

import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.WeatherForecast
import by.startandroid.weatherforecastdemo.data.local.Local
import by.startandroid.weatherforecastdemo.data.remote.Remote

class Repository (private val remote: Remote, private val local: Local) {

    suspend fun getWeather(cityname: String, key: String, units: String, lang: String): WeatherForecast {
        var weatherData = remote.getWeather(cityname, key, units, lang)
        try {
            if (weatherData != null) {
                local.insertWeatherForecast(weatherData)
            }
        } catch (e: Exception) {
            if (weatherData != null) {
                local.updateWeatherForecast(weatherData)
            }
        }
        if (weatherData == null) {
            weatherData = local.getOneWeatherForecast(cityname)
        }
        return weatherData
    }

    suspend fun insertCityName(cityName: CityName) {
        local.insertCityName(cityName)
    }

    suspend fun getAllName(): MutableList<CityName> {
        return local.getAllName()
    }

    suspend fun getCityName(name: String): MutableList<CityName>{
        return local.getCityName(name)
    }

    suspend fun getNameList(): MutableList<String> {
        return local.getNameList()
    }

    suspend fun deleteAllCityName() {
        local.deleteAllCityName()
    }

    suspend fun deleteOneCityName(name: CityName) {
        local.deleteOneCityName(name)
    }

    suspend fun getAllWeatherForecast(): MutableList<WeatherForecast> {
        return local.getAllWeatherForecast()
    }

    suspend fun deleteAllWeatherForecast() {
        local.deleteAllWeatherForecast()
    }

    suspend fun updateAllWeatherForecast(weatherForecast: MutableList<WeatherForecast>) {
        local.updateAllWeatherForecast(weatherForecast)
    }
}
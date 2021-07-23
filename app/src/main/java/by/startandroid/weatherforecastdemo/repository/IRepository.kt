package by.startandroid.weatherforecastdemo.repository

import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.WeatherForecast

interface IRepository {

    suspend fun getWeather(cityname: String, key: String, units: String, lang: String): WeatherForecast?


    suspend fun insertCityName(cityName: CityName)

    suspend fun getAllName(): MutableList<CityName>

    suspend fun getCityName(name: String): CityName?

    suspend fun getNameList(): MutableList<String>

    suspend fun getName(): String?

    suspend fun deleteAllCityName()

    suspend fun deleteOneCityName(name: CityName)

    suspend fun updateCityNameForWidget(name: String, isSelected: Int)

    suspend fun selectName(): String?


    suspend fun getAllWeatherForecast(): MutableList<WeatherForecast>

    suspend fun getOneWeatherForecast(name: String): WeatherForecast

    suspend fun deleteAllWeatherForecast()

    suspend fun updateAllWeatherForecast(weatherForecast: MutableList<WeatherForecast>)

    suspend fun deleteOneWeatherForecast(name: String)
}
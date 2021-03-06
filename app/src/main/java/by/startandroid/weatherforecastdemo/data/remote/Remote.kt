package by.startandroid.weatherforecastdemo.data.remote

import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.DataSource
import by.startandroid.weatherforecastdemo.data.WeatherForecast

class Remote(): DataSource {
    private val apiService = IApiService.create()

    override suspend fun getWeather(cityname: String, key: String, units: String, lang: String
    ): WeatherForecast? {
        return try {
            val weatherData = apiService.getWeather(cityname, key, units, lang)
            weatherData
        } catch (e: Exception) {
            val weather: WeatherForecast? = null
            weather
        }
    }

    override suspend fun insertCityName(cityName: CityName) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllName(): MutableList<CityName> {
        TODO("Not yet implemented")
    }

    override suspend fun getCityName(name: String): CityName? {
        TODO("Not yet implemented")
    }

    override suspend fun getNameList(): MutableList<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getName(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllCityName() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOneCityName(name: CityName) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCityNameForWidget(name: String, isSelected: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun selectName(): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertWeatherForecast(weatherForecast: WeatherForecast) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWeatherForecast(): MutableList<WeatherForecast> {
        TODO("Not yet implemented")
    }

    override suspend fun getOneWeatherForecast(name: String): WeatherForecast {
        TODO("Not yet implemented")
    }

    override suspend fun getNameFromWeatherForecast(name: String): String? {
        TODO("Not yet implemented")
    }

    override suspend fun updateWeatherForecast(weatherForecast: WeatherForecast) {
        TODO("Not yet implemented")
    }

    override suspend fun updateAllWeatherForecast(weatherForecast: MutableList<WeatherForecast>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllWeatherForecast() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOneWeatherForecast(name: String) {
        TODO("Not yet implemented")
    }
}
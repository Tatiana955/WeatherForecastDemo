package by.startandroid.weatherforecastdemo.data.remote

import by.startandroid.weatherforecastdemo.data.WeatherForecast

class Remote () {
    private val apiService = IApiService.create()

    suspend fun getWeather(cityname: String, key: String, units: String, lang: String
    ): WeatherForecast? {
        return try {
            val weatherData = apiService.getWeather(cityname, key, units, lang)
            weatherData
        } catch (e: Exception) {
            val weather: WeatherForecast? = null
            weather
        }
    }
}
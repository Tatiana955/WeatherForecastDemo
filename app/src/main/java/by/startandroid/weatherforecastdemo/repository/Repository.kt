package by.startandroid.weatherforecastdemo.repository

import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.DataSource
import by.startandroid.weatherforecastdemo.data.WeatherForecast

class Repository (private val remote: DataSource, private val local: DataSource): IRepository {

    override suspend fun getWeather(cityname: String, key: String, units: String, lang: String): WeatherForecast {
        var weatherData = remote.getWeather(cityname, key, units, lang)
        val name = local.getNameFromWeatherForecast(cityname)

        if (weatherData != null && name == null) {
            local.insertWeatherForecast(weatherData)
        } else if (weatherData != null && name != null) {
            local.updateWeatherForecast(weatherData)
        }

        if (weatherData == null) {
            weatherData = local.getOneWeatherForecast(cityname)
        }
        return weatherData
    }

    // CityName

    override suspend fun insertCityName(cityName: CityName) {
        local.insertCityName(cityName)
    }

    override suspend fun getAllName(): MutableList<CityName> {
        return local.getAllName()
    }

    override suspend fun getCityName(name: String): CityName? {
        return local.getCityName(name)
    }

    override suspend fun getNameList(): MutableList<String> {
        return local.getNameList()
    }

    override suspend fun getName(): String? {
        return local.getName()
    }

    override suspend fun deleteAllCityName() {
        local.deleteAllCityName()
    }

    override suspend fun deleteOneCityName(name: CityName) {
        local.deleteOneCityName(name)
    }

    override suspend fun updateCityNameForWidget(name: String, isSelected: Int) {
        local.updateCityNameForWidget(name, isSelected)
    }

    override suspend fun selectName(): String? {
        return local.selectName()
    }


    // WeatherForecast

    override suspend fun getAllWeatherForecast(): MutableList<WeatherForecast> {
        return local.getAllWeatherForecast()
    }

    override suspend fun getOneWeatherForecast(name: String): WeatherForecast {
        return local.getOneWeatherForecast(name)
    }

    override suspend fun deleteAllWeatherForecast() {
        local.deleteAllWeatherForecast()
    }

    override suspend fun updateAllWeatherForecast(weatherForecast: MutableList<WeatherForecast>) {
        local.updateAllWeatherForecast(weatherForecast)
    }

    override suspend fun deleteOneWeatherForecast(name: String) {
        local.deleteOneWeatherForecast(name)
    }
}
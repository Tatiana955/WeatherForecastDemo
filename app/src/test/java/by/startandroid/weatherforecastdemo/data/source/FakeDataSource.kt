package by.startandroid.weatherforecastdemo.data.source

import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.DataSource
import by.startandroid.weatherforecastdemo.data.WeatherForecast

class FakeDataSource(var cityNameList: MutableList<CityName>? = mutableListOf()): DataSource {

    override suspend fun getWeather(cityname: String, key: String, units: String, lang: String): WeatherForecast? {
        TODO("Not yet implemented")
    }

    override suspend fun insertCityName(cityName: CityName) {
        cityNameList?.add(cityName)
    }

    override suspend fun getAllName(): MutableList<CityName> {
        return cityNameList!!
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
        cityNameList?.clear()
    }

    override suspend fun deleteOneCityName(name: CityName) {
        cityNameList?.remove(name)
    }

    override suspend fun updateCityNameForWidget(name: String, isSelected: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun selectName(): String? {
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
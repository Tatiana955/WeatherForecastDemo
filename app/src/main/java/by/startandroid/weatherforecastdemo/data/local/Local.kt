package by.startandroid.weatherforecastdemo.data.local

import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.DataSource
import by.startandroid.weatherforecastdemo.data.WeatherForecast

class Local internal constructor(val weatherDao: WeatherDao): DataSource {

    override suspend fun getWeather(cityname: String, key: String, units: String, lang: String): WeatherForecast? {
        TODO("Not yet implemented")
    }

    // CityName

    override suspend fun insertCityName(cityName: CityName) {
        weatherDao.insertCityName(cityName)
    }

    override suspend fun getAllName(): MutableList<CityName> {
        return weatherDao.getAllName()
    }

    override suspend fun getCityName(name: String): CityName? {
        return weatherDao.getCityName(name)
    }

    override suspend fun getNameList(): MutableList<String> {
        return weatherDao.getNameList()
    }

    override suspend fun getName(): String? {
        return weatherDao.getName()
    }

    override suspend fun deleteAllCityName() {
        weatherDao.deleteAllCityName()
    }

    override suspend fun deleteOneCityName(name: CityName) {
        weatherDao.deleteOneCityName(name)
    }

    override suspend fun updateCityNameForWidget(name: String, isSelected: Int) {
        weatherDao.updateCityNameForWidget(name, isSelected)
    }

    override suspend fun selectName(): String? {
        return weatherDao.selectName()
    }


    // WeatherForecast

    override suspend fun insertWeatherForecast(weatherForecast: WeatherForecast) {
        weatherDao.insertWeatherForecast(weatherForecast)
    }

    override suspend fun getAllWeatherForecast(): MutableList<WeatherForecast> {
        return weatherDao.getAllWeatherForecast()
    }

    override suspend fun getOneWeatherForecast(name: String): WeatherForecast {
        return weatherDao.getOneWeatherForecast(name)
    }

    override suspend fun getNameFromWeatherForecast(name: String): String? {
        return weatherDao.getNameFromWeatherForecast(name)
    }

    override suspend fun updateWeatherForecast(weatherForecast: WeatherForecast) {
        weatherDao.updateWeatherForecast(weatherForecast)
    }

    override suspend fun updateAllWeatherForecast(weatherForecast: MutableList<WeatherForecast>) {
        weatherDao.updateAllWeatherForecast(weatherForecast)
    }

    override suspend fun deleteAllWeatherForecast() {
        weatherDao.deleteAllWeatherForecast()
    }

    override suspend fun deleteOneWeatherForecast(name: String) {
        weatherDao.deleteOneWeatherForecast(name)
    }
}
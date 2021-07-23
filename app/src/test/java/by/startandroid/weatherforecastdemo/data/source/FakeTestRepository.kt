package by.startandroid.weatherforecastdemo.data.source

import androidx.lifecycle.MutableLiveData
import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.WeatherForecast
import by.startandroid.weatherforecastdemo.repository.IRepository
import kotlinx.coroutines.runBlocking

class FakeTestRepository: IRepository {
    var serviceData: LinkedHashMap<String, CityName> = LinkedHashMap()
    private val observableCityName = MutableLiveData<MutableList<CityName>>()

    override suspend fun getWeather(cityname: String, key: String, units: String, lang: String): WeatherForecast? {
        TODO("Not yet implemented")
    }

    override suspend fun insertCityName(cityName: CityName) {
        observableCityName.value?.add(cityName)
    }

    override suspend fun getAllName(): MutableList<CityName> {
        return serviceData.values.toMutableList()
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
        observableCityName.value?.remove(name)
    }

    override suspend fun updateCityNameForWidget(name: String, isSelected: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun selectName(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWeatherForecast(): MutableList<WeatherForecast> {
        TODO("Not yet implemented")
    }

    override suspend fun getOneWeatherForecast(name: String): WeatherForecast {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllWeatherForecast() {
        TODO("Not yet implemented")
    }

    override suspend fun updateAllWeatherForecast(weatherForecast: MutableList<WeatherForecast>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOneWeatherForecast(name: String) {
        TODO("Not yet implemented")
    }

    fun addCityName(vararg cityName: CityName) {
        for (i in cityName) {
            serviceData[i.name] = i
        }
        runBlocking { refreshCityName() }
    }

    private suspend fun refreshCityName() {
        observableCityName.value = getAllName()
    }
}
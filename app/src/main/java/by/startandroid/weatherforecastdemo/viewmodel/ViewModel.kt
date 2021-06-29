package by.startandroid.weatherforecastdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.WeatherForecast
import by.startandroid.weatherforecastdemo.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel (private val repository: Repository): ViewModel(){

    val weatherDataLive: MutableLiveData<WeatherForecast?> by lazy {
        MutableLiveData<WeatherForecast?>()
    }
    val cityNameLive: MutableLiveData<MutableList<CityName>> by lazy {
        MutableLiveData<MutableList<CityName>>()
    }
    var selectedCityName: String? = null

    /**
     * Once you have your key, replace the "your_key" string.
     */
    fun getWeather(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getWeather(selectedCityName!!,
                    "your_key", "metric", "ru")
            weatherDataLive.postValue(data)
        }
    }

    fun insertCityName(cityName: CityName) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertCityName(cityName)
            cityNameLive.value!!.add(cityName)
        }
    }

    fun getAllName() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllName()
            cityNameLive.postValue(data)
        }
    }

    fun deleteAllCityName() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAllCityName()
        }
    }

    fun deleteOneCityName(name: CityName) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteOneCityName(name)
            cityNameLive.value!!.remove(name)
        }
    }
}
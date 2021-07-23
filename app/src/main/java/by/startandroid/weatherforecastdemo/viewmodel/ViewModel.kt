package by.startandroid.weatherforecastdemo.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.startandroid.weatherforecastdemo.Key
import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.WeatherForecast
import by.startandroid.weatherforecastdemo.repository.IRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel (val repository: IRepository): ViewModel(), LifecycleObserver {

    val weatherDataLive: MutableLiveData<WeatherForecast?> by lazy {
        MutableLiveData<WeatherForecast?>()
    }
    val cityNameLive: MutableLiveData<MutableList<CityName>> by lazy {
        MutableLiveData<MutableList<CityName>>()
    }
    var selectedCityName: String? = null

    //  Once you have your key, replace the "Key().key" string.
    fun getWeather(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = selectedCityName?.let { repository.getWeather(it, Key().key, "metric", "ru") }
            weatherDataLive.postValue(data)
        }
    }

    // CityName

    fun insertCityName(cityName: CityName) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertCityName(cityName)
            cityNameLive.value?.add(cityName)
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
            cityNameLive.value?.clear()
        }
    }

    fun deleteOneCityName(name: CityName) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteOneCityName(name)
            cityNameLive.value?.remove(name)
        }
    }

    fun updateCityNameForWidget(name: String, isSelected: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateCityNameForWidget(name, isSelected)
        }
    }


    // WeatherForecast

    fun deleteAllWeatherForecast() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAllWeatherForecast()
        }
    }

    fun deleteOneWeatherForecast(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteOneWeatherForecast(name)
        }
    }
}
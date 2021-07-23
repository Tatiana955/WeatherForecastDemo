package by.startandroid.weatherforecastdemo.ui

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.*
import by.startandroid.weatherforecastdemo.Key
import by.startandroid.weatherforecastdemo.data.WeatherForecast
import by.startandroid.weatherforecastdemo.repository.IRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope

@HiltWorker
class WeatherWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: IRepository?
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        val weather = mutableListOf<WeatherForecast>()
        try {
            var listCityName: MutableList<String>? = null
            listCityName?.clear()
            listCityName = repository?.getNameList()

            val dataWeather = mutableListOf<WeatherForecast>()

            if (listCityName != null) {
                for (i in listCityName) {
                    //  Once you have your key, replace the "Key().key" string.
                    val data = repository?.getWeather(i, Key().key, "metric", "ru")
                    if (data != null) {
                        dataWeather.add(data)
                    }
                }
                weather.addAll(dataWeather)
                dataWeather.clear()
            }
            repository?.updateAllWeatherForecast(weather)
            weather.clear()
            Result.success()
        } catch (e: Exception) {
            Log.d("!!!e", e.toString())
            Result.retry()
        }
    }
}
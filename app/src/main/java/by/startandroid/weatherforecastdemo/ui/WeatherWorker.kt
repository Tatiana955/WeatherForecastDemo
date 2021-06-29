package by.startandroid.weatherforecastdemo.ui

import android.content.Context
import android.util.Log
import androidx.work.*
import by.startandroid.weatherforecastdemo.data.WeatherForecast
import by.startandroid.weatherforecastdemo.data.local.Local
import by.startandroid.weatherforecastdemo.data.remote.Remote
import by.startandroid.weatherforecastdemo.repository.Repository
import kotlinx.coroutines.coroutineScope

class WeatherWorker (context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        val repository = Repository(Remote(), Local(applicationContext))
        try {
            val listCityName = repository.getNameList()
            val weather = mutableListOf<WeatherForecast>()
            for (i in listCityName) {
                val data = repository.getWeather(i, "1ac3b63eb4315ff2783d8bc44a994a56",
                        "metric", "ru")
                weather.add(data)
            }
            repository.updateAllWeatherForecast(weather)
//            Log.d("!!!success", "success")
            Result.success()
        } catch (e: Exception) {
            Log.d("!!!e", e.toString())
            Result.retry()
        }
    }
}
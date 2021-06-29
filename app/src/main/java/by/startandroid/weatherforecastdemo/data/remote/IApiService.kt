package by.startandroid.weatherforecastdemo.data.remote

import by.startandroid.weatherforecastdemo.data.WeatherForecast
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface IApiService {
    @GET("weather")
    suspend fun getWeather(
            @Query("q") cityname: String,
            @Query("appid") key: String,
            @Query("units") units: String,
            @Query("lang") lang: String
    ) : WeatherForecast

    companion object Factory {
        fun create(): IApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(IApiService::class.java)
        }
    }
}
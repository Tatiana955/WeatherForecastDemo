package by.startandroid.weatherforecastdemo.data

import androidx.room.*

@Entity(tableName = "weather_forecast")
data class WeatherForecast(
        @Embedded val coord: Coordinates,
        val base: String,
        @Embedded val main: Main,
        val visibility: Int,
        @Embedded val wind: Wind,
        @Embedded val clouds: Clouds,
        val timezone: Int,
        @PrimaryKey val id: Long,
        val name: String,
        val cod: Int
        )

@Entity (foreignKeys = [ForeignKey(
        entity = WeatherForecast::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("forecast_id"))])
data class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String,
        @PrimaryKey @ColumnInfo (name = "forecast_id") val forecastId: Long
        )

data class Coordinates(
        val lon: Double,
        val lat: Double
        )

data class Main(
        val temp: Double,
        val feels_like: Double,
        val temp_min: Double,
        val temp_max: Double,
        val pressure: Int,
        val humidity: Int
        )

data class Wind(
        val speed: Double,
        val deg: Int
        )

data class Clouds (
        val all: Int
        )
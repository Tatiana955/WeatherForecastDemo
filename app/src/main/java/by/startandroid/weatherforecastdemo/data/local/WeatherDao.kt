package by.startandroid.weatherforecastdemo.data.local

import androidx.room.*
import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.data.WeatherForecast

@Dao
interface WeatherDao {

    // CityName

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityName(cityName: CityName)

    @Query("SELECT * FROM city_name")
    suspend fun getAllName(): MutableList<CityName>

    @Query ("SELECT * FROM city_name WHERE name = :name")
    suspend fun getCityName(name: String): CityName?

    @Query("SELECT name FROM city_name")
    suspend fun getNameList(): MutableList<String>

    @Query("SELECT name FROM city_name")
    suspend fun getName(): String?

    @Query("DELETE FROM city_name")
    suspend fun deleteAllCityName()

    @Delete
    suspend fun deleteOneCityName(name: CityName)

    @Query("UPDATE city_name SET isSelectedWidget = :isSelected WHERE name = :name")
    suspend fun updateCityNameForWidget(name: String, isSelected: Int)

    @Query("SELECT name FROM city_name WHERE isSelectedWidget = 1")
    suspend fun selectName(): String?


    // WeatherForecast

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForecast(weatherForecast: WeatherForecast)

    @Query ("SELECT * FROM weather_forecast")
    suspend fun getAllWeatherForecast(): MutableList<WeatherForecast>

    @Query ("SELECT * FROM weather_forecast WHERE name = :name")
    suspend fun getOneWeatherForecast(name: String): WeatherForecast

    @Query ("SELECT name FROM weather_forecast WHERE name = :name")
    suspend fun getNameFromWeatherForecast(name: String): String?

    @Update
    suspend fun updateWeatherForecast(weatherForecast: WeatherForecast)

    @Update
    suspend fun updateAllWeatherForecast(weatherForecast: MutableList<WeatherForecast>)

    @Query("DELETE FROM weather_forecast")
    suspend fun deleteAllWeatherForecast()

    @Query("DELETE FROM weather_forecast WHERE name = :name")
    suspend fun deleteOneWeatherForecast(name: String)

    @Query ("SELECT weather_forecast.name FROM weather_forecast, weather " +
            "WHERE weather_forecast.id == weather.forecast_id AND weather.description == :description")
    suspend fun getWeatherForecastNameOfWeather (description: String): String?
}
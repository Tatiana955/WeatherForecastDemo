package by.startandroid.weatherforecastdemo.data

interface DataSource {

    suspend fun getWeather(cityname: String, key: String, units: String, lang: String): WeatherForecast?


    suspend fun insertCityName(cityName: CityName)

    suspend fun getAllName(): MutableList<CityName>

    suspend fun getCityName(name: String): CityName?

    suspend fun getNameList(): MutableList<String>

    suspend fun getName(): String?

    suspend fun deleteAllCityName()

    suspend fun deleteOneCityName(name: CityName)

    suspend fun updateCityNameForWidget(name: String, isSelected: Int)

    suspend fun selectName(): String?


    suspend fun insertWeatherForecast(weatherForecast: WeatherForecast)

    suspend fun getAllWeatherForecast(): MutableList<WeatherForecast>

    suspend fun getOneWeatherForecast(name: String): WeatherForecast

    suspend fun getNameFromWeatherForecast(name: String): String?

    suspend fun updateWeatherForecast(weatherForecast: WeatherForecast)

    suspend fun updateAllWeatherForecast(weatherForecast: MutableList<WeatherForecast>)

    suspend fun deleteAllWeatherForecast()

    suspend fun deleteOneWeatherForecast(name: String)
}
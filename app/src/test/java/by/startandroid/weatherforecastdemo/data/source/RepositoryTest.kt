package by.startandroid.weatherforecastdemo.data.source

import by.startandroid.weatherforecastdemo.MainCoroutineRule
import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryTest {
    private val cityName1 = CityName("CityName1", 0)
    private val cityName2 = CityName("CityName2", 0)
    private val cityName3 = CityName("CityName3", 0)
    private val remoteCityNames = listOf(cityName1, cityName2).sortedBy { it.name }
    private val localCityNames = listOf(cityName3).sortedBy { it.name }

    private lateinit var remoteDataSource: FakeDataSource
    private lateinit var localDataSource: FakeDataSource
    private lateinit var repository: Repository

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createRepository() {
        remoteDataSource = FakeDataSource(remoteCityNames.toMutableList())
        localDataSource = FakeDataSource(localCityNames.toMutableList())
        repository = Repository(remoteDataSource, localDataSource)
    }

    @Test
    fun getAllName_requestsAllCityNameFromLocalDataSource() = mainCoroutineRule.runBlockingTest {
        val cityNames = repository.getAllName()
        assertThat(cityNames, IsEqual(localCityNames))
    }

    @Test
    fun insertCityName_addCityNameInLocalDataSource() = mainCoroutineRule.runBlockingTest {
        repository.insertCityName(cityName1)
        val cityNames = repository.getAllName()
        assertThat(cityNames, hasItem(cityName1))
    }

    @Test
    fun deleteAllCityName_deleteAllCityNamesOfLocalDataSource() = mainCoroutineRule.runBlockingTest {
        repository.deleteAllCityName()
        val cityNames = repository.getAllName()
        assertTrue(cityNames.isEmpty())
    }

    @Test
    fun deleteOneCityName_deleteOneCityNameOfRemoteDataSource() = mainCoroutineRule.runBlockingTest {
        repository.deleteOneCityName(cityName1)
        val cityNames = repository.getAllName()
        assertThat(cityNames, not(hasItem(cityName1)))
    }
}
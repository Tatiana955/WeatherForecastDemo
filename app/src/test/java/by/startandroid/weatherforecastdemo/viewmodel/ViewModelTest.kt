package by.startandroid.weatherforecastdemo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import by.startandroid.weatherforecastdemo.data.*
import by.startandroid.weatherforecastdemo.data.source.FakeTestRepository
import by.startandroid.weatherforecastdemo.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import by.startandroid.weatherforecastdemo.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.*

@ExperimentalCoroutinesApi
class ViewModelTest {

    private lateinit var iRepository: FakeTestRepository
    private lateinit var viewModel: ViewModel

    private val cityName1 = CityName("CityName1", 0)
    private val cityName2 = CityName("CityName2", 0)
    private val cityName3 = CityName("CityName3", 0)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setupViewModel() {
        iRepository = FakeTestRepository()
        iRepository.addCityName(cityName1, cityName2, cityName3)
        viewModel = ViewModel(iRepository)
    }

    @Test
    fun getAllName_requestsAllCityName() = mainCoroutineRule.runBlockingTest {
            viewModel.getAllName()
            val value = viewModel.cityNameLive.getOrAwaitValue()
            assertThat(value, (not(nullValue())))
    }

    @Test
    fun insertCityName_addCityName() = mainCoroutineRule.runBlockingTest {
        val cityName4 = CityName("CityName4", 0)
        viewModel.insertCityName(cityName4)
        viewModel.getAllName()
        val value = viewModel.cityNameLive.getOrAwaitValue()
        assertThat(value, CoreMatchers.hasItem(cityName4))
    }

    @Test
    fun deleteOneCityName_deleteOneCityName() = mainCoroutineRule.runBlockingTest {
        viewModel.deleteOneCityName(cityName1)
        viewModel.getAllName()
        val value = viewModel.cityNameLive.getOrAwaitValue()
        assertThat(value, not(hasItem(cityName1)))
    }
}
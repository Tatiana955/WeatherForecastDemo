package by.startandroid.weatherforecastdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.startandroid.weatherforecastdemo.repository.IRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (private val repository: IRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel(repository) as T
    }
}
package by.startandroid.weatherforecastdemo.ui.settings

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import androidx.work.*
import by.startandroid.weatherforecastdemo.R
import by.startandroid.weatherforecastdemo.ui.MainActivity
import by.startandroid.weatherforecastdemo.ui.WeatherWorker
import by.startandroid.weatherforecastdemo.viewmodel.ViewModel
import java.util.concurrent.TimeUnit

class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var viewModel: ViewModel
    private var workRequestBuilder: PeriodicWorkRequest.Builder? = null
    private var workRequest: WorkRequest? = null
    private lateinit var workManager: WorkManager
    private val list = mutableListOf<String>()
    private var repeatInterval: Long = 1
    private var name: String? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        viewModel = ViewModelProvider(activity as MainActivity).get(ViewModel::class.java)

        val switchPreference: SwitchPreferenceCompat? = findPreference("sync")
        val listPreference: ListPreference? = findPreference("reply")
        val listPreferenceWidget: ListPreference? = findPreference("widget")

        viewModel.getAllName()
        viewModel.cityNameLive.observe(activity as MainActivity, Observer {
            list.clear()
            for (i in it) {
                list.add(i.name)
            }

            listPreferenceWidget?.entries = list.toTypedArray()
            listPreferenceWidget?.entryValues = list.toTypedArray()

            name = listPreferenceWidget?.entry.toString()

            for (i in list) {
                if (i != name) {
                    viewModel.updateCityNameForWidget(i, 0)
                } else {
                    viewModel.updateCityNameForWidget(i, 1)
                }
            }
        })

        val entry = listPreference?.entry

        repeatInterval = try {
            Integer.parseInt(entry.toString()).toLong()
        } catch (e: NumberFormatException) {
            1
        }

        workManager = WorkManager.getInstance(requireContext())

        when (switchPreference?.isChecked) {
            true -> startWorker()
            false -> stopWorker()
        }
    }

    private fun startWorker() {
        workRequestBuilder = PeriodicWorkRequestBuilder<WeatherWorker>(repeatInterval, TimeUnit.HOURS)
        workRequest = workRequestBuilder?.build()

        workRequest?.let { workRequest ->
            workManager.enqueue(workRequest)
            workManager.getWorkInfoByIdLiveData(workRequest.id)
                    .observe(this, {
                        Log.d("!!!state", it.state.toString())
                        if (it?.state == WorkInfo.State.SUCCEEDED) {
                            Toast.makeText(activity as MainActivity, "Work completed", Toast.LENGTH_SHORT).show()
                        }
                    })
        }
    }

    private fun stopWorker() {
        workRequest?.id?.let { workManager.cancelWorkById(it) }
    }
}
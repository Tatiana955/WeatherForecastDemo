package by.startandroid.weatherforecastdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import by.startandroid.weatherforecastdemo.R
import by.startandroid.weatherforecastdemo.data.local.Local
import by.startandroid.weatherforecastdemo.data.remote.Remote
import by.startandroid.weatherforecastdemo.databinding.ActivityMainBinding
import by.startandroid.weatherforecastdemo.repository.Repository
import by.startandroid.weatherforecastdemo.viewmodel.ViewModel
import by.startandroid.weatherforecastdemo.viewmodel.ViewModelFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navController = supportFragmentManager.findFragmentById(R.id.navHost)?.findNavController()!!
        setupActionBarWithNavController(navController, binding.drawerLayout)
        binding.toolbar.setupWithNavController(navController, binding.drawerLayout)
        binding.navView.setupWithNavController(navController)

        val remote = Remote()
        val local = Local(this)
        val repository = Repository(remote, local)
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ViewModel::class.java)

        val workRequest: WorkRequest = PeriodicWorkRequestBuilder<WeatherWorker>(1, TimeUnit.HOURS).build()
        val workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueue(workRequest)
        workManager.getWorkInfoByIdLiveData(workRequest.id)
                .observe(this, {
                    Log.d("!!!state", it.state.toString())
                    if(it?.state == WorkInfo.State.SUCCEEDED) {
                        Toast.makeText(this, "Work completed", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
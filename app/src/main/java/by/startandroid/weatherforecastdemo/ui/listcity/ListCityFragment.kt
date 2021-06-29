package by.startandroid.weatherforecastdemo.ui.listcity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.startandroid.weatherforecastdemo.R
import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.databinding.FragmentListCityBinding
import by.startandroid.weatherforecastdemo.ui.MainActivity
import by.startandroid.weatherforecastdemo.viewmodel.ViewModel

class ListCityFragment : Fragment() {
    private var binding: FragmentListCityBinding? = null
    private lateinit var navController: NavController
    private lateinit var adapter: ListCityAdapter
    private lateinit var viewModel: ViewModel
    private val listCity = mutableListOf<CityName>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCityBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(activity as MainActivity).get(ViewModel::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        adapter = ListCityAdapter(listCity, this)
        binding!!.recyclerView.adapter = adapter
        binding!!.recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.cityNameLive.observe(activity as MainActivity, Observer {
            listCity.clear()
            listCity.addAll(it)
            adapter.notifyDataSetChanged()
        })

        binding!!.buttonAdd.setOnClickListener {
            navController.navigate(R.id.addCityFragment)
        }

        binding!!.buttonClear.setOnClickListener {
            viewModel.deleteAllCityName()
            listCity.clear()
            adapter.notifyDataSetChanged()
        }
    }

    fun cityNameSelect(position: Int) {
        viewModel.selectedCityName = listCity[position].name
        navController.navigate(R.id.weatherFragment)
    }

    fun deleteOneCityName(position: Int) {
        viewModel.deleteOneCityName(listCity[position])
        Toast.makeText(context, "Город удалён", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        viewModel.getAllName()
        super.onResume()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
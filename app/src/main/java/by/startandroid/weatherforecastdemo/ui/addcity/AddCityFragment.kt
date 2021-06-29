package by.startandroid.weatherforecastdemo.ui.addcity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.startandroid.weatherforecastdemo.data.CityName
import by.startandroid.weatherforecastdemo.databinding.FragmentAddCityBinding
import by.startandroid.weatherforecastdemo.ui.MainActivity
import by.startandroid.weatherforecastdemo.viewmodel.ViewModel

class AddCityFragment : Fragment() {
    private var binding: FragmentAddCityBinding? = null
    private lateinit var navController: NavController
    private lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddCityBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(activity as MainActivity).get(ViewModel::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        binding!!.buttonAdd.setOnClickListener {
            val cityName = CityName(
                binding!!.editText.text.toString()
            )
            viewModel.insertCityName(cityName)
            navController.popBackStack()
            Toast.makeText(context, "Город добавлен", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
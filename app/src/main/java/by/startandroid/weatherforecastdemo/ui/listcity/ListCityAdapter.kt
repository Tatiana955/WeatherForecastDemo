package by.startandroid.weatherforecastdemo.ui.listcity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.startandroid.weatherforecastdemo.R
import by.startandroid.weatherforecastdemo.data.CityName

class ListCityAdapter (private val list: MutableList<CityName>,
                       val fragment: ListCityFragment
                       ): RecyclerView.Adapter<ListCityAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.city_layout, parent, false)
        val holder = MyViewHolder(itemView)

        holder.itemView.setOnClickListener {
            fragment.cityNameSelect(holder.adapterPosition)
        }

        holder.imageView.setOnClickListener {
            val position = holder.adapterPosition
            fragment.deleteOneCityName(position)
            holder.imageView.visibility = View.GONE
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
package com.example.app.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.model.Bike
import com.squareup.picasso.Picasso


class BikeAdapter(var context: Context, var bikes: List<Bike> = arrayListOf()) :
    RecyclerView.Adapter<BikeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BikeAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = bikes.size

    override fun onBindViewHolder(viewHolder: BikeAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindBike(bikes[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item
        fun bindBike(bike: Bike) {

            itemView.findViewById<TextView>(R.id.bike_name).text = bike.name
            itemView.findViewById<TextView>(R.id.bike_price).text = "$${bike.price.toString()}"

          //  Picasso.get().load(bike.photos[0].filename).fit().into(itemView.findViewById<ImageView>(R.id.bike_image) as ImageView)
        }

    }

}

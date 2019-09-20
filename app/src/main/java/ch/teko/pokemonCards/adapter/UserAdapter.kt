package ch.sik.teko.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.teko.pokemonCards.R
import ch.teko.pokemonCards.model.Json4Kotlin_Base
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_catched.view.*

class UserAdapter(private val callback: AdapterCallback): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = mutableListOf<Json4Kotlin_Base>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catched, parent, false)
        return UserViewHolder(view)
    }

    fun setData(data: MutableList<Json4Kotlin_Base>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun addData(item: Json4Kotlin_Base) {
        data.add(item)
        notifyItemInserted(data.size -1)
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    fun removeItem(index: Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position >= 0) {
            val user = data[position]

            if (holder is UserViewHolder) {
                holder.bind(user, callback)
            }
        }

    }


    private class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val transform = CropCircleTransformation()

        fun bind(card: Json4Kotlin_Base, callback: AdapterCallback) {
            //Load the image to the corresponding image view
            Picasso.get().load(card.sprites.front_default)
                .transform(transform)
                .into(itemView.user_img)
            //Set the user name
            itemView.pokemon_name.text = card.name
            //Bind the adapter click
            itemView.setOnClickListener {
                callback.onItemClicked(card, adapterPosition)
            }
        }
    }

    interface AdapterCallback {

        /**
         * This is the callback for the adapter click
         */
        fun onItemClicked(card:Json4Kotlin_Base, position:Int)

    }
}
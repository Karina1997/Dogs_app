package com.example.dods_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.ItemClickListener
import com.example.dods_app.R
import com.example.dods_app.factories.ListContentFactory
import com.squareup.picasso.Picasso
import java.util.*

class CardsAdapter(private val listContentFactory: ListContentFactory) :
    RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {
    private val cards: MutableList<Card>

    init {
        cards = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_item_view, parent, false)
        return CardViewHolder(view, listContentFactory.getClickHandler()::onItemClick)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val (title, imagePath) = cards[position]
        holder.title.text = title
        Picasso.get().load(imagePath).resize(0, 100).into(holder.image)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun addCards(items: List<Card>) {
        listContentFactory.addList(items.map { it.name })
        cards.addAll(items)
    }

    class CardViewHolder(itemView: View, val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById<View>(R.id.card_title) as TextView
        var image: ImageView = itemView.findViewById<View>(R.id.image) as ImageView

        init {
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }

    }

}
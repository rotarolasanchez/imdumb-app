package com.example.imdumb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.imdumb.databinding.ItemActorBinding
import com.example.imdumb.domain.model.ActorModel

class ActorAdapter(private val actors: List<ActorModel>) : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemActorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actors[position]
        holder.binding.tvActorName.text = actor.name
        holder.binding.tvCharacterName.text = actor.character
        
        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        Glide.with(holder.itemView.context)
            .load(actor.profilePath)
            .circleCrop()
            .placeholder(circularProgressDrawable)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.binding.ivActorProfile)
    }

    override fun getItemCount() = actors.size
}

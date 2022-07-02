package com.abir.hasan.androidtdd.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.databinding.AdapterPlaylistItemBinding

class MyPlaylistRecyclerViewAdapter(
    private val values: List<Playlist>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            AdapterPlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: AdapterPlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var playlist: Playlist

        init {
            binding.root.setOnClickListener { listener.invoke(playlist.id) }
        }

        fun onBind(item: Playlist) {
            playlist = item
            binding.tvPlaylistName.text = playlist.name
            binding.tvPlaylistCategory.text = playlist.category
            binding.ivPlaylistImage.setImageResource(playlist.image)
        }
    }

}
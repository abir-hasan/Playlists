package com.abir.hasan.androidtdd.ui.playlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.data.remote.PlaylistAPI
import com.abir.hasan.androidtdd.data.remote.PlaylistService
import com.abir.hasan.androidtdd.databinding.FragmentPlaylistBinding
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }

    private var _binding: FragmentPlaylistBinding? = null

    private val binding: FragmentPlaylistBinding
        get() = _binding!!

    private lateinit var viewModel: PlaylistViewModel

    private lateinit var viewModelFactory: PlaylistViewModelFactory

    private val retrofit = Retrofit.Builder()
        //.baseUrl("http://127.0.0.1:3000/") // Check local IP
        .baseUrl("http://10.0.2.2:3000/") // Check local IP
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(PlaylistAPI::class.java)
    private val service = PlaylistService(api)
    private val repository = PlaylistRepository(service)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObserver(view)
    }

    private fun setupObserver(view: View) {
        Log.d("TEST", "setupObserver() called")
        viewModel.playLists.observe(viewLifecycleOwner) { lists ->
            lists.getOrNull()?.let {
                setupList(view, it)
            } ?: run {
                Log.e("TEST", "setupObserver() error")
            }
        }
    }

    private fun setupList(
        view: View,
        lists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            Log.d("TEST", "setupList() called")
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MyPlaylistRecyclerViewAdapter(lists)
        }
    }

    private fun setupViewModel() {
        viewModelFactory = PlaylistViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistViewModel::class.java]
    }
}
package com.abir.hasan.androidtdd.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.abir.hasan.androidtdd.R
import com.abir.hasan.androidtdd.databinding.FragmentPlaylistDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    private var _binding: FragmentPlaylistDetailBinding? = null

    private val binding: FragmentPlaylistDetailBinding
        get() = _binding!!

    private val args: PlaylistDetailsFragmentArgs by navArgs()

    private val viewModel: PlaylistDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePlaylistDetails()
        observePlaylistDetailsLoader()
        getData()
    }

    private fun observePlaylistDetailsLoader() {
        viewModel.detailsLoader.observe(viewLifecycleOwner) { loading ->
            binding.pbDetails.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }

    private fun getData() {
        val playlistId = args.playlistId
        viewModel.getPlaylistDetails(playlistId)
    }

    private fun observePlaylistDetails() {
        viewModel.playListDetails.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                binding.tvPlaylistName.text = result.getOrNull()?.name
                binding.tvPlaylistDetails.text = result.getOrNull()?.details
            } else {
                Snackbar.make(
                    binding.root,
                    R.string.generic_error,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}
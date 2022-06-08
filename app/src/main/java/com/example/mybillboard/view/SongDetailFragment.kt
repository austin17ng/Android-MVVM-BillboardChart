package com.example.mybillboard.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mybillboard.databinding.FragmentSongDetailBinding
import com.example.mybillboard.viewmodel.SongDetailViewModel
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "songId"
private const val ARG_PARAM2 = "transitionName"

/**
 * A simple [Fragment] subclass.
 * Use the [SongDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var songId: String? = null
    private var transitionName: String? = null

    private var _binding: FragmentSongDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SongDetailViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, SongDetailViewModel.Factory(activity.application))
            .get(SongDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            songId = it.getString(ARG_PARAM1)
            transitionName = it.getString(ARG_PARAM2)
        }
//        val animation = TransitionInflater.from(context).inflateTransition(
//            android.R.transition.move
//        )
//        sharedElementEnterTransition = animation
//        sharedElementReturnTransition = animation
//        postponeEnterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSongDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        lifecycleScope.launch {
            songId?.let { viewModel.fetchSong(it) }
        }

        viewModel.navigateToSpotify.observe(viewLifecycleOwner, Observer { uri ->
            if (uri != null) {
                openSpotify(uri)
                viewModel.openSpotifyDone()
            }
        }
        )

        viewModel.navigateToMessenger.observe(viewLifecycleOwner, Observer { text ->
            if (text != null) {
                shareOnMessenger(text)
                viewModel.openMessengerDone()
            }
        })
    }

    fun openSpotify(uri: String) {
        val launcher = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(launcher)
    }

    fun shareOnMessenger(text: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            text
        )
        sendIntent.type = "text/plain"
        sendIntent.setPackage("com.facebook.orca")
        startActivity(sendIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SongDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
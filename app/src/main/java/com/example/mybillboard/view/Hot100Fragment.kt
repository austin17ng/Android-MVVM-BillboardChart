package com.example.mybillboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mybillboard.R
import com.example.mybillboard.ChartItemAdapter
import com.example.mybillboard.databinding.FragmentHot100Binding
import com.example.mybillboard.domain.ChartItem
import com.example.mybillboard.util.createNotification
import com.example.mybillboard.viewmodel.Hot100ViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Hot100Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHot100Binding? = null
    private val binding get() = _binding!!

    private val viewModel: Hot100ViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, Hot100ViewModel.Factory(activity.application))
            .get(Hot100ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHot100Binding.inflate(inflater, container, false)
        binding.rvResults.adapter = ChartItemAdapter(
            ChartItemAdapter.OnSongCLickListener { searchResult, image ->
                navigateToSongDetail(searchResult, image)
            }
        )
        binding.textView2.setOnClickListener {
            createNotification(requireContext())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.items.observe(viewLifecycleOwner,  Observer { items ->
            val adapter = binding.rvResults.adapter as ChartItemAdapter
            adapter.submitList(items)
        }
        )

        viewModel.isNetworkError.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                onNetworkError()
            }
        })
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show()
            viewModel.onNetworkErrorShown()
        }
    }

    private fun navigateToSongDetail(chartItem: ChartItem, img: View) {
        val bundle = bundleOf("songId" to chartItem.id, "transitionName" to chartItem.id)
        findNavController().navigate(R.id.action_hot100Fragment_to_songDetailFragment, bundle, null, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Hot100Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
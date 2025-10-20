package com.pranish.s8069126assignment2.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import com.pranish.s8069126assignment2.databinding.FragmentDashboardBinding
import com.pranish.s8069126assignment2.R
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val vm: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = EntityAdapter(onClick = { entity ->
            findNavController().navigate(R.id.action_dashboard_to_details, Bundle().apply {
                putString("title", entity.title())
                putString("artist", entity.subtitle())
                putString("desc", entity.description ?: "")
            })
        })
        binding.rvEntities.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEntities.adapter = adapter

        val keypass = arguments?.getString("keypass") ?: ""
        vm.load(keypass)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect { st ->
                    when (st) {
                        is DashboardUiState.Loading -> {
                            binding.progress.visibility = View.VISIBLE
                            binding.tvMsg.visibility = View.VISIBLE
                            binding.tvMsg.text = "Loading dashboard..."
                        }
                        is DashboardUiState.Success -> {
                            binding.progress.visibility = View.GONE
                            binding.tvMsg.visibility = View.GONE
                            adapter.submitList(st.entities)
                            if (st.entities.isEmpty()) {
                                binding.tvMsg.visibility = View.VISIBLE
                                binding.tvMsg.text = "No data from server."
                            }
                        }
                        is DashboardUiState.Error -> {
                            binding.progress.visibility = View.GONE
                            binding.tvMsg.visibility = View.VISIBLE
                            binding.tvMsg.text = "Error: " + st.message
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun onDestroyView() { _binding = null; super.onDestroyView() }
}

package com.pranish.s8069126assignment2.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import com.pranish.s8069126assignment2.R
import com.pranish.s8069126assignment2.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val vm: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            val campus = binding.etCampus.text.toString().trim().lowercase()
            val user = binding.etUsername.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()
            vm.login(campus, user, pass)
        }
        collectState()
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect { st ->
                    when (st) {
                        is LoginUiState.Idle -> binding.tvStatus.text = ""
                        is LoginUiState.Loading -> binding.tvStatus.text = "Logging in..."
                        is LoginUiState.Success -> {
                            binding.tvStatus.text = "Success: " + st.keypass
                            findNavController().navigate(R.id.action_login_to_dashboard, Bundle().apply {
                                putString("keypass", st.keypass)
                            })
                        }
                        is LoginUiState.Error -> binding.tvStatus.text = "Error: " + st.message
                    }
                }
            }
        }
    }

    override fun onDestroyView() { _binding = null; super.onDestroyView() }
}

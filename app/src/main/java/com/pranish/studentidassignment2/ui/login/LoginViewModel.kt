package com.pranish.s8069126assignment2.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.pranish.s8069126assignment2.data.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    data class Success(val keypass: String) : LoginUiState
    data class Error(val message: String) : LoginUiState
}

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    private val _state = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val state: StateFlow<LoginUiState> = _state

    fun login(campus: String, username: String, password: String) {
        _state.value = LoginUiState.Loading
        viewModelScope.launch {
            runCatching { repo.login(campus, username, password) }
                .onSuccess { _state.value = LoginUiState.Success(it.keypass) }
                .onFailure { _state.value = LoginUiState.Error(it.message ?: "Login failed") }
        }
    }
}

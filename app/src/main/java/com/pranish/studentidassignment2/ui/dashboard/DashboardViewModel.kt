package com.pranish.s8069126assignment2.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.pranish.s8069126assignment2.data.Repo
import com.pranish.s8069126assignment2.network.dto.EntityDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface DashboardUiState {
    object Idle : DashboardUiState
    object Loading : DashboardUiState
    data class Success(val entities: List<EntityDto>) : DashboardUiState
    data class Error(val message: String) : DashboardUiState
}

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    private val _state = MutableStateFlow<DashboardUiState>(DashboardUiState.Idle)
    val state: StateFlow<DashboardUiState> = _state

    fun load(keypass: String) {
        _state.value = DashboardUiState.Loading
        viewModelScope.launch {
            runCatching { repo.fetchDashboard(keypass) }
                .onSuccess { _state.value = DashboardUiState.Success(it.entities) }
                .onFailure { _state.value = DashboardUiState.Error(it.message ?: "Failed to load") }
        }
    }
}

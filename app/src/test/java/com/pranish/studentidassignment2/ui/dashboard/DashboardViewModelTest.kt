package com.pranish.s8069126assignment2.ui.dashboard

import com.pranish.s8069126assignment2.data.Repo
import com.pranish.s8069126assignment2.network.dto.DashboardResponse
import com.pranish.s8069126assignment2.network.dto.EntityDto
import com.pranish.s8069126assignment2.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {
    @get:Rule val mainRule = MainDispatcherRule()
    private val repo = mockk<Repo>()
    private val vm = DashboardViewModel(repo)

    @Test fun `load success emits Success with entities`() = runTest {
        val list = listOf(EntityDto(property1 = "A", property2 = "B", description = "C"))
        coEvery { repo.fetchDashboard("topic") } returns DashboardResponse(list, list.size)
        vm.load("topic")
        advanceUntilIdle()
        assertTrue(vm.state.value is DashboardUiState.Success)
    }

    @Test fun `load failure emits Error`() = runTest {
        coEvery { repo.fetchDashboard("topic") } throws RuntimeException("Nope")
        vm.load("topic")
        advanceUntilIdle()
        assertTrue(vm.state.value is DashboardUiState.Error)
    }
}

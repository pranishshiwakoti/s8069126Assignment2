package com.pranish.s8069126assignment2.ui.login

import com.pranish.s8069126assignment2.data.Repo
import com.pranish.s8069126assignment2.network.dto.LoginResponse
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
class LoginViewModelTest {
    @get:Rule val mainRule = MainDispatcherRule()
    private val repo = mockk<Repo>()
    private val vm = LoginViewModel(repo)

    @Test fun `login success emits Success`() = runTest {
        coEvery { repo.login("sydney", any(), any()) } returns LoginResponse("topicName123")
        vm.login("sydney", "pranish", "8069126")
        advanceUntilIdle()
        assertTrue(vm.state.value is LoginUiState.Success)
    }

    @Test fun `login failure emits Error`() = runTest {
        coEvery { repo.login("sydney", any(), any()) } throws RuntimeException("Invalid")
        vm.login("sydney", "x", "y")
        advanceUntilIdle()
        assertTrue(vm.state.value is LoginUiState.Error)
    }
}

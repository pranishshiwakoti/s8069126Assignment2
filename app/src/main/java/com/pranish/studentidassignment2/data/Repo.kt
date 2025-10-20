package com.pranish.s8069126assignment2.data

import com.pranish.s8069126assignment2.network.NitApi
import com.pranish.s8069126assignment2.network.dto.*
import javax.inject.Inject

class Repo @Inject constructor(private val api: NitApi) {
    suspend fun login(campus: String, username: String, password: String) =
        api.login(campus, LoginRequest(username, password))

    suspend fun fetchDashboard(keypass: String) =
        api.getDashboard(keypass)
}

package com.pranish.s8069126assignment2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pranish.s8069126assignment2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

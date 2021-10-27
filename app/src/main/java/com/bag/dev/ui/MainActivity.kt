package com.bag.dev.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bag.dev.R
import com.bag.dev.ui.fragments.SongFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.containerFrame, SongFragment())
            .commit()
    }
}

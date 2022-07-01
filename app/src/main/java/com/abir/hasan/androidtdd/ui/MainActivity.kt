package com.abir.hasan.androidtdd.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abir.hasan.androidtdd.R
import com.abir.hasan.androidtdd.ui.playlist.PlaylistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, PlaylistFragment.newInstance())
                .commit()
        }
    }
}
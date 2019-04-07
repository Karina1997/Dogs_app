package com.example.dods_app.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dods_app.R
import com.example.dods_app.Router
import com.example.dods_app.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router = Router(this, R.id.fragment_container)
        if (savedInstanceState == null) router.navigateTo(false, ::MainFragment)
    }

    override fun onBackPressed() {
        if (!router.navigateBack()) {
            super.onBackPressed()
        }
    }
}

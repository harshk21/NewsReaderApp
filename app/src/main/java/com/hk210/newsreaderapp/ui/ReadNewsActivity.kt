package com.hk210.newsreaderapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hk210.newsreaderapp.databinding.ReadNewsActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadNewsActivity : AppCompatActivity() {

    private var _binding: ReadNewsActivityBinding? = null
    private val binding: ReadNewsActivityBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        _binding = ReadNewsActivityBinding.inflate(layoutInflater)
        binding.root.apply {
            setContentView(this)
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
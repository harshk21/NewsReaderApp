package com.hk210.newsreaderapp.utils.network

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.hk210.newsreaderapp.databinding.NoInternetLayoutBinding

class NetworkDialog(context: Context) : Dialog(context) {

    private var _binding: NoInternetLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = NoInternetLayoutBinding.inflate(layoutInflater)
        binding.root.apply {
            setContentView(this)
            window?.setLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}
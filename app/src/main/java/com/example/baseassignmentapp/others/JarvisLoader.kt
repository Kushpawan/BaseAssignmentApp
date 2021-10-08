package com.example.baseassignmentapp.others

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import com.example.baseassignmentapp.R
import kotlinx.android.synthetic.main.global_loader_layout.*

class JarvisLoader(context: Context, private val message: String?) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.global_loader_layout)
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        message?.let {
            loader_message_text.text = it
        }
    }
}
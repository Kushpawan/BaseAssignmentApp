package com.example.baseassignmentapp.others

import android.content.Context

open class LoadingUtils {
    companion object {
        private var jarvisLoader: JarvisLoader? = null
        fun showDialog(
            context: Context?,
            message: String? = null,
            isCancelable: Boolean? = true
        ) {
            hideDialog()
            if (context != null) {
                try {
                    jarvisLoader = JarvisLoader(context, message)
                    jarvisLoader?.let { jarvisLoader ->
                        jarvisLoader.setCanceledOnTouchOutside(true)
                        jarvisLoader.setCancelable(isCancelable ?: true)
                        jarvisLoader.show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun hideDialog() {
            if (jarvisLoader != null && jarvisLoader?.isShowing!!) {
                jarvisLoader = try {
                    jarvisLoader?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }

    }

}
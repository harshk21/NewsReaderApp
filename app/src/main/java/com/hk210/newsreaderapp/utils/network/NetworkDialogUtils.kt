package com.hk210.newsreaderapp.utils.network

import android.content.Context

object NetworkDialogUtils {

    private var networkDialog: NetworkDialog? = null

    fun showDialog(
        context: Context?,
        isCancelable: Boolean
    ) {
        hideDialog()
        if (context != null) {
            try {
                networkDialog = NetworkDialog(context)
                networkDialog?.let { networkDialog ->
                    networkDialog.setCanceledOnTouchOutside(true)
                    networkDialog.setCancelable(isCancelable)
                    networkDialog.show()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun hideDialog() {
        if (networkDialog != null && networkDialog?.isShowing!!) {
            networkDialog = try {
                networkDialog?.dismiss()
                null
            } catch (e: Exception) {
                null
            }
        }
    }
}

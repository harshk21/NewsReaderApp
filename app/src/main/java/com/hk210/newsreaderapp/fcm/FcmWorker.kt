package com.hk210.newsreaderapp.fcm

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

const val TAG = "WorkManager"
class FcmWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        Log.d(TAG, "Performing long running task in scheduled job")

        return Result.success()
    }
}

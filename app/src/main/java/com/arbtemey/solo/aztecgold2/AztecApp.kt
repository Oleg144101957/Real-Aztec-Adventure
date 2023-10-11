package com.arbtemey.solo.aztecgold2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper


@HiltAndroidApp
class AztecApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initPaperStorage()
    }

    private fun initPaperStorage() {
        Paper.init(this)
    }
}
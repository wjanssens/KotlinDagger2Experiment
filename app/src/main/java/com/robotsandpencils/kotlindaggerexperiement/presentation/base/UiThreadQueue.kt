package com.robotsandpencils.kotlindaggerexperiement.presentation.base

import android.os.Handler
import android.os.Looper

open class UiThreadQueue : ThreadQueue(AndroidHandlerRunner(Handler(Looper.getMainLooper())))

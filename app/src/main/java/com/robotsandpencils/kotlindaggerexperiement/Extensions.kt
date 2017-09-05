package com.robotsandpencils.kotlindaggerexperiement

import android.app.Activity

/**
 * Created by nealsanche on 2017-09-05.
 */

val Activity.app: App
    get() = application as App
package com.robotsandpencils.kotlindaggerexperiement.app.repositories

import com.robotsandpencils.kotlindaggerexperiement.net.xkcd.XkcdAPI
import com.robotsandpencils.kotlindaggerexperiement.net.xkcd.XkcdResponse
import io.reactivex.Single

class XkcdRepository(val api: XkcdAPI) {
    fun getLatestComic(): Single<XkcdResponse> = api.getLatestComic()
    fun getComic(num: Int): Single<XkcdResponse> = api.getComic(num)
}

package com.robotsandpencils.kotlindaggerexperiement.net.xkcd

import com.google.gson.annotations.SerializedName

/*
{
    "month": "9",
    "num": 1894,
    "link": "",
    "year": "2017",
    "news": "",
    "safe_title": "Real Estate",
    "transcript": "",
    "alt": "I tried converting the prices into pizzas, to put it in more familiar terms, and it just became a hard-to-think-about number of pizzas.",
    "img": "https://imgs.xkcd.com/comics/real_estate.png",
    "title": "Real Estate",
    "day": "25"
}
*/
data class XkcdResponse(val month: String,
                        val num: Int,
                        val link: String,
                        val year: String,
                        val news: String,
                        @SerializedName("safe_title") val safeTitle: String,
                        val transcript: String,
                        val alt: String,
                        val img: String,
                        val title: String,
                        val day: String)
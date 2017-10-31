package com.robotsandpencils.kotlindaggerexperiement.app.repositories

import io.reactivex.Flowable
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ClockRepository {

    fun getList(): Flowable<List<String>> {
        return Flowable.zip(listOf(getTrad(), getHex(), getDec()), { arr -> arr.asList().map { i -> i.toString() }})
    }

    fun getTrad(): Flowable<String> {
        return Single.fromCallable({
            SimpleDateFormat("HH:mm").format(System.currentTimeMillis())
        }).delay(250, TimeUnit.MILLISECONDS).repeat()
    }


    fun getHex(): Flowable<String> {
        return Single.fromCallable({
            //milliseconds to hexadecimal (FF:FF)
            val offset = Calendar.getInstance().get(Calendar.ZONE_OFFSET)
            var ms = (System.currentTimeMillis() + offset) % 86_400_000

            // 1/16 day (hex hour) = 1 h 30 m
            val a = Math.floor(ms / 5_400_000.0).toLong()
            ms -= 5_400_000 * a
            // 1/256 day (hex maxime) = 5 m 37.5 s
            val b = Math.floor(ms / 337_500.0).toLong()
            ms -= 337_500 * b
            // 1/4096 day (hex minute) ~= 21 seconds
            val c = Math.floor(ms / 21_093.75).toLong()
            ms -= (21_093.75 * c).toLong()
            // 1/65536 day (hex second) ~= 1.32 seconds
            val d = Math.floor(ms / 1_318.3593).toLong()

            "%X%X:%X%X".format(a, b, c, d)
        }).delay(330, TimeUnit.MILLISECONDS).repeat()
    }

    fun getDec(): Flowable<String> {
        return Single.fromCallable({
            //milliseconds to hexadecimal (.99999)
            val offset = Calendar.getInstance().get(Calendar.ZONE_OFFSET)
            var ms = (System.currentTimeMillis() + offset) % 86_400_000

            // 1/10 day = 2 h 24 m
            val a = Math.floor(ms / 8_640_000.0).toLong()
            ms -= 8_640_000 * a
            // 1/100 day = 14 m 24 s
            val b = Math.floor(ms / 864_000.0).toLong()
            ms -= 864_000 * b
            // 1/1000 day = 1 m 26.4 seconds
            val c = Math.floor(ms / 86_400.0).toLong()
            ms -= 86_400 * c
            // 1/10000 day (hex second) = 8.64 seconds
            val d = Math.floor(ms / 8_640.0).toLong()
            ms -= 8_640 * d
            // 1/10000 day (hex second) = 864 ms
            val e = Math.floor(ms / 864.0).toLong()

            ".%d%d%d%d%d".format(a, b, c, d, e)
        }).delay(216, TimeUnit.MILLISECONDS).repeat()
    }
}
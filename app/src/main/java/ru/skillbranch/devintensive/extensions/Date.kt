package ru.skillbranch.devintensive.extensions

import android.renderscript.RSInvalidStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        else -> throw RSInvalidStateException("invalid unit")

    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()) : String {
    var s:String =""
    val diff= -this.time.toLong()+date.time.toLong()
    when (diff){
        in 0..SECOND -> s="только что"
        in SECOND..45*SECOND -> s="несколько секунд назад"
        in 45*SECOND..75*SECOND -> s="минуту назад"
        in 75*SECOND..45*MINUTE -> s="${(diff/ MINUTE)} минут назад"
        in 45*MINUTE..75*MINUTE -> s="час назад"
        in 75*MINUTE..22*HOUR -> s="${(diff/ HOUR)} часов назад"
        in 22*HOUR..26*HOUR -> s="день назад"
        in 26*HOUR..360*DAY -> s="${(diff/ DAY)} дней назад"
        else -> s="более года назад"
    }
    return s
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
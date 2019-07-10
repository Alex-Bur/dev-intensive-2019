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
fun declension(count: Long, type: TimeUnits) : String {
    var one =""
    var two =""
    var five =""
    var amount = count

    when (type) {
        TimeUnits.SECOND -> {
            one = "секунду"
            two = "секунды"
            five = "секунд"
        }
        TimeUnits.MINUTE -> {
            one = "минута"
            two = "минуты"
            five = "минут"
        }
        TimeUnits.HOUR -> {
            one = "час"
            two = "часа"
            five = "часов"
        }
        TimeUnits.DAY -> {
            one = "день"
            two = "дня"
            five = "дней"
        }
    }

    if (amount > 100) amount = amount.rem(100)
    if (amount > 20) amount = amount.rem(10)

    if (amount in (1..1)) return one
    if (amount in (2..4)) return two
    return five

}

fun Date.humanizeDiff(date:Date = Date()) : String {
    var s:String =""
    val diff= -this.time.toLong()+date.time.toLong()
    when (diff){
        in 0..SECOND -> s="только что"
        in SECOND..45*SECOND -> s="несколько секунд назад"
        in 45*SECOND..75*SECOND -> s="минуту назад"
        in 75*SECOND..45*MINUTE -> s="${(diff/ MINUTE)} ${declension(diff/ MINUTE, TimeUnits.MINUTE)} назад"
        in 45*MINUTE..75*MINUTE -> s="час назад"
        in 75*MINUTE..22*HOUR -> s="${(diff/ HOUR)} ${declension(diff/ HOUR, TimeUnits.HOUR)} назад"
        in 22*HOUR..26*HOUR -> s="день назад"
        in 26*HOUR..360*DAY -> s="${(diff/ DAY)} ${declension(diff/ DAY, TimeUnits.DAY)} назад"
        in 360*DAY..10000*DAY -> s="более года назад"
        in -45*SECOND..-SECOND -> s="через несколько секунд"
        in -75*SECOND..-45*SECOND -> s="через минуту"
        in -45*MINUTE..-75*SECOND -> s="через ${-(diff/ MINUTE)} ${declension(-diff/ MINUTE, TimeUnits.MINUTE)}"
        in -75*MINUTE..-45*MINUTE -> s="через час"
        in -22*HOUR..-75*MINUTE -> s="через ${-(diff/ HOUR)} ${declension(-diff/ HOUR, TimeUnits.HOUR)}"
        in -26*HOUR..-22*HOUR -> s="через день"
        in -360*DAY..-26*HOUR -> s="через ${-(diff/ DAY)} ${declension(-diff/ DAY, TimeUnits.DAY)}"
        in -10000*DAY..-360*DAY -> s="более чем через год"
    }
    return s
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(count: Long) : String {
        return "$count ${declension(count, this)}"
    }
}

fun String.truncate(len: Int = 16) : String {
    var s = this

    s = s.trimEnd()
    if (s?.length<=len) return s

    return s.substring(0,len).trimEnd() +"..."

}
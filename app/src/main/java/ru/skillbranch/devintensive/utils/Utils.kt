package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.size == null || parts?.getOrNull(0) == "") null else parts?.getOrNull(0)
        val lastName = if (parts?.size != null && parts?.size >1 && fullName?.length>1) parts?.getOrNull(1) else null
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String =" "): String {
       var s: String = ""
        for (i in (0..payload.length-1))
        s+= when (payload.substring(i,i+1)) {
            "а"-> "a"
            "б"-> "b"
            "в"-> "v"
            "г"-> "g"
            "д"-> "d"
            "е"-> "e"
            "ё"-> "e"
            "ж"-> "zh"
            "з"-> "z"
            "и"-> "i"
            "й"-> "i"
            "к"-> "k"
            "л"-> "l"
            "м"-> "m"
            "н"-> "n"
            "о"-> "o"
            "п"-> "p"
            "р"-> "r"
            "с"-> "s"
            "т"-> "t"
            "у"-> "u"
            "ф"-> "f"
            "х"-> "h"
            "ц"-> "c"
            "ч"-> "ch"
            "ш"-> "sh"
            "щ"-> "sh'"
            "ъ"-> ""
            "ы"-> "i"
            "ь"-> ""
            "э"-> "e"
            "ю"-> "yu"
            "я"-> "ya"
            "А"-> "A"
            "Б"-> "B"
            "В"-> "V"
            "Г"-> "G"
            "Д"-> "D"
            "Е"-> "E"
            "Ё"-> "E"
            "Ж"-> "Zh"
            "З"-> "Z"
            "И"-> "I"
            "Й"-> "I"
            "К"-> "K"
            "Л"-> "L"
            "М"-> "M"
            "Н"-> "N"
            "О"-> "O"
            "П"-> "P"
            "Р"-> "R"
            "С"-> "S"
            "Т"-> "T"
            "У"-> "U"
            "Ф"-> "F"
            "Х"-> "H"
            "Ц"-> "C"
            "Ч"-> "Ch"
            "Ш"-> "Sh"
            "Щ"-> "Sh'"
            "Ъ"-> ""
            "Ы"-> "I"
            "Ь"-> ""
            "Э"-> "E"
            "Ю"-> "Yu"
            "Я"-> "Ya"
            else -> payload.substring(i,i+1)
        }
        s = s.replace(" ", divider)
        return s
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
	var s1: String?=""
    var s2: String?=""
    if (firstName?.isNotEmpty() == true) s1 = firstName?.substring(0,1)?.toUpperCase()
    if (lastName?.isNotEmpty() == true) s2 = lastName?.substring(0,1)?.toUpperCase()
//    println("'$s1' '$s2'")
    if ((s2=="" || s2==" ") && (s1=="" || s1==" ")) {
        s1 = null
        s2 = ""
    }
    return s1+s2

    }
}
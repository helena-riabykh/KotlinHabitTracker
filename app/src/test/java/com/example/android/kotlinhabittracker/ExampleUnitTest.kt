package com.example.android.kotlinhabittracker

import org.junit.Test

/**
 * "aaabbbbccb"
 * a:3
 * b:5
 * c:2
 */
class ExampleUnitTest {

    //    private fun item_count(str: String) {
////        val ch1 = str.toCharArray()
////        ch1.sort()
////        val hashMap = mutableMapOf<Char, Int>()
////        var count = 1
////        var index = 0
////        while (index < ch1.size - 1) {
////            if (ch1[index] == ch1[index + 1]) {
////                count++
////                index++
////                if (index == ch1.size - 1) hashMap.put(ch1[index], count)
////            } else {
////                hashMap.put(ch1[index], count)
////                count = 1
////                index++
////                if (index == ch1.size - 1) {
////                    hashMap.put(ch1[index ], count)
////                }
////            }
////        }
////        for ((key, value) in hashMap) {
////            println(" $key: $value")
////        }


    private fun isAnagram(s: String, t: String): Boolean {
        var arrayS: CharArray = s.toCharArray()
        var arrayT: CharArray = t.toCharArray()
        arrayS.sort()
        arrayT.sort()
        println(arrayS)
        println(arrayT)
        return arrayS contentEquals arrayT
    }


    @Test
    fun main() {
        val test = ExampleUnitTest()
        println(test.isAnagram("аасс", "ссас"))
    }
}

//var count = 0
//       var n = t
//        val mapaS: MutableMap<Int, Char> = mutableMapOf()
//        val mapaT: MutableMap<Int, Char> = mutableMapOf()
//        if (s.count() == t.count()) {
//            for (index in s.indices) {
//                var count = 0
//                for (char in s.indices) {
//                    if (s[index] == s[char]) {
//                        count++
////                       n = n.replace(n[char].toString(), "")
// //                       println(count)
// //                       println(n)
////                        break@loop
//                    }
//                }
//                mapaS[count] = s[index]
//                println(count)
//                println(s[index])
//            }
//            for (index in t.indices) {
//                var count = 0
//                 for (char in t.indices) {
//                    if (t[index] == t[char]) {
//                        count++
////                       n = n.replace(n[char].toString(), "")
// //                       println(count)
//                        //                       println(n)
// //                       break@loop
//                    }
//                }
//                mapaT[count] = t[index]
//            }
//        }
//        return mapaS == mapaT

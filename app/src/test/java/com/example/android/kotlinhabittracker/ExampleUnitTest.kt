package com.example.android.kotlinhabittracker

import org.junit.Test

import org.junit.Assert.*

/**
 * "aaabbbbccb"
 * a:3
 * b:5
 * c:2
 */
class ExampleUnitTest {

    private fun item_count(str: String) {
        val ch1 = str.toCharArray()
        ch1.sort()
        val hashMap = mutableMapOf<Char, Int>()
        var count = 1
        var index = 0
        while (index < ch1.size - 1) {
            if (ch1[index] == ch1[index + 1]) {
                count++
                index++
                if (index == ch1.size - 1) hashMap.put(ch1[index], count)
            } else {
                hashMap.put(ch1[index], count)
                count = 1
                index++
                if (index == ch1.size - 1) {
                    hashMap.put(ch1[index ], count)
                }
            }
        }
        for ((key, value) in hashMap) {
            println(" $key: $value")
        }
    }

    @Test
    fun main() {
        item_count("aaababb")
    }
}


//println("Sorted array: ${ch1.contentToString()}")
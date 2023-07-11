package com.example.nutritiontracker20.utils

/*
    for women: BMR = 655 + (9.6 × body weight in kg) + (1.8 × body height in cm) - (4.7 × age in years);
    for men: BMR = 66 + (13.7 × weight in kg) + (5 × height in cm) - (6.8 × age in years).
*/

fun kcalCalculator(gender: eGender, height: Int, weight: Int, age: Int): Int {
    val res: Int

    if(gender.equals(eGender.FEMALE)) {
        res = (655 + (9.6f * weight) + (1.8f * height) - (4.7f * age)).toInt()
    } else {
        res = (655 + (13.7f * weight) + (5 * height) - (6.8f * age)).toInt()
    }
    return res
}
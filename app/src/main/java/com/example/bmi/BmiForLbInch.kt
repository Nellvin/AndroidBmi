package com.example.bmi

class BmiForLbInch(var mass: Double, var height: Int) : Bmi {
    override fun countBmi() = mass * 703 / (height * height)
}

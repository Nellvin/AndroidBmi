package com.example.bmi

class BmiForKgCm(var mass: Double, var height: Int) : Bmi{
    override fun countBmi() = mass * 10000.0 / (height * height)
}
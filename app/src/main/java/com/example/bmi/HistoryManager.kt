package com.example.bmi

class HistoryManager() {
    var his = ArrayList<String>()


    fun getArray(): ArrayList<String> {
        return his
    }

    fun addToArray(item: String) {
        if (his.size == 10)
            his.removeAt(0)
        his.add(item)
    }

    override fun toString(): String {
        var s: String = ""
        for ((index, value) in his.withIndex()) {
            if (index != his.size - 1)
                s += (value + "//")
            else
                s += value
        }
        return s
    }

    fun tooArrayList(str:String){
        val arr=str.split("//")
        for (value in arr) {
            his.add(value)
        }
    }


}
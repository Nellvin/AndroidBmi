package com.example.bmi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity() {
    var europMesure: Boolean = true
    val hir = Historyy()
    var prefs: SharedPreferences? = null

    companion object MainActivity {
        const val maxKg = 160
        const val minKg = 40
        const val maxCm = 220
        const val minCm = 140
        const val maxIn = 83
        const val minIn = 56
        const val maxLb = 290
        const val minLb = 90

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var height: Double //= 0.0
        var heightString: String// = ""
        var weight: Double// = 0.0
        var weightString: String// = ""
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Hisotry recovery
        prefs = this.getSharedPreferences("com.example.bmi.prefs", 0)
        val stringss = prefs!!.getString("HISTORY", "")
        Log.i("maainCREATE", stringss)

        hir.tooArrayList(stringss)


        button.setOnClickListener {
            weightString = editText2.text.toString()
            heightString = editText.text.toString()

            if (!heightString.equals(""))
                height = heightString.toDouble()
            else
                height = 0.0
            if (!weightString.equals(""))
                weight = weightString.toDouble()
            else
                weight = 0.0

            var bmiCount = 0.0

            if (europMesure) {
                if (height > maxCm || height < minCm) {
                    editText.error = "Height must be between " + minCm + "-" + maxCm
                }
                if (weight > maxKg || weight < minKg) {
                    editText2.error = "Weight must be between " + minKg + "-" + maxKg
                }
                if (!(height > maxCm || height < minCm || weight > maxKg || weight < minKg)) {
                    var bmiForKgCm = BmiForKgCm(weight, height.toInt())
                    bmiCount = bmiForKgCm.countBmi() * 100
                    bmiCount = bmiCount.toInt() * 1.0
                    bmiCount = bmiCount / 100
                }
            } else {
                if (height > maxIn || height < minIn) {
                    editText.error = "Height must be between " + minIn + "-" + maxIn
                }
                if (weight > maxLb || weight < minLb) {
                    editText2.error = "Weight must be between " + minLb + "-" + maxLb
                }
                if (!(height > maxIn || height < minIn || weight > maxLb || weight < minLb)) {
                    var bmiForLbInch = BmiForLbInch(weight, height.toInt())
                    bmiCount = bmiForLbInch.countBmi() * 100
                    bmiCount = bmiCount.toInt() * 1.0
                    bmiCount = bmiCount / 100
                }
            }

            var bmiText: String = ""
            if (bmiCount <= 15)
                bmiText = "Very severely underweight"
            if (bmiCount > 15 && bmiCount <= 16)
                bmiText = "Severely underweight"
            if (bmiCount > 16 && bmiCount <= 18.5)
                bmiText = "Underweight"
            if (bmiCount > 18.5 && bmiCount <= 25)
                bmiText = "Healthy weight"
            if (bmiCount > 25 && bmiCount <= 30)
                bmiText = "Overweight"
            if (bmiCount > 30 && bmiCount <= 35)
                bmiText = "Moderately obese"
            if (bmiCount > 35 && bmiCount <= 40)
                bmiText = "Severely obese"
            if (bmiCount > 40 && bmiCount <= 45)
                bmiText = "Very severely obese"
            if (bmiCount > 45 && bmiCount <= 50)
                bmiText = "Morbidly Obese"
            if (bmiCount > 50 && bmiCount <= 60)
                bmiText = "Super Obese"
            if (bmiCount > 60) {
                bmiText = "Hyper Obese"
            }
            if (bmiCount != 0.0) {
                textView2.text = bmiCount.toString()
                textView.text = bmiText
                when {
                    bmiCount <= 15 -> textView.setTextColor(resources.getColor(R.color.colorJakisMaloZdrowy))
                    bmiCount <= 18.5 -> textView.setTextColor(resources.getColor(R.color.colorJakisLapisLazuli))
                    bmiCount <= 25 -> textView.setTextColor(resources.getColor(R.color.colorJakisGrynszpan))
                    bmiCount <= 30.0 -> textView.setTextColor(resources.getColor(R.color.colorJakisRóżPompejański))
                    else -> textView.setTextColor(resources.getColor(R.color.colorJakiśGruby))
                }

                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
//                val currentDate = sdf.format(Date())
                if (europMesure)
                    hir.addToArray(heightString + "|" + weightString + "|" + "kg/cm|" + bmiCount + "|" + Date().date + "." + (Date().month + 1) + "." + (Date().year + 1900) + "\n" + Date().hours + ":" + Date().minutes + ":" + Date().seconds + "|" + textView.currentTextColor)
                else
                    hir.addToArray(heightString + "|" + weightString + "|" + "lb/inch |" + bmiCount + "|" + Date().date + "." + (Date().month + 1) + "." + (Date().year + 1900) + "\n" + Date().hours + ":" + Date().minutes + ":" + Date().seconds + "|" + textView.currentTextColor)
            }
        }
        imageButton5.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("bmiValue", textView2.text.toString())
            intent.putExtra("bmiValueInfo", textView.text.toString())

            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.main_menu_abourMe) {
            val intent = Intent(this, AboutMeActivity::class.java)
            startActivity(intent)
        }
        if (item!!.itemId == R.id.main_menu_history) {
            val intent = Intent(this, HistoryActivity::class.java)

            val putExtra = intent.putExtra("arr", hir.getArray())
            startActivity(intent)
        }
        if (item.itemId == R.id.main_menu_changeMesures) {
            if (europMesure) {
                textView3.setText(R.string.Heightinch)
                textView4.setText(R.string.Weightlb)
                europMesure = false
            } else {
                textView3.setText(R.string.Heightcm)
                textView4.setText(R.string.Weightkg)
                europMesure = true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun openAboutMe() {

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putString("Heigh", editText.text.toString())
            putString("Weigh", editText2.text.toString())
            putString("DoubleBmi", textView2.text.toString())
            putString("StringBmi", textView.text.toString())
            putString("StringBmicolor", textView.currentTextColor.toString())


        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
//        editText.setText(savedInstanceState!!.getString("Height"))
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                editText.setText((getString("Height")))
                editText2.setText(getString("Weight"))
                textView2.text = getString("DoubleBmi")
                textView.text = getString("StringBmi")
                textView.setTextColor(getString(("StringBmicolor")).toInt())
            }
        }

    }

    override fun onStop() {
        val editor = prefs!!.edit()
        editor.putString("HISTORY", hir.toString())
        Log.i("maainDESTROY", hir.toString())
        editor.apply()

        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
//Aplikacja ma pamietać osattnie 10 operacje w pasku na gorze
//Hisotry otiwera ekran z listą, ostatnich wyników
// Data pomiaru, masa, wzrost, BMI w kolorze
//Listview nie używać :)
//Recycleview
//Mają sie tylko wyświetlać
//Mają sie pamietac jak wyjdziesz z Apki
//Schared preferences
//Mają sie tylko zapisywać valid
//wywalić stare shard, dodać nowe
//Warto pamietac jednoski
//zapamietywać dubla jako string
//ContextCompat
///osobny watek dla shered prefs
//setColor
//android:configChanges="orientation|screenSize|keyboardHidden"



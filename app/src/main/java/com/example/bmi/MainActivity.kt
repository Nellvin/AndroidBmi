package com.example.bmi

import android.content.Intent
import android.icu.text.IDNA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var europMesure: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        var height: Double //= 0.0
        var heightString: String// = ""
        var weight: Double// = 0.0
        var weightString: String// = ""
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            weightString = editText2.text.toString()
            heightString = editText.text.toString()
            //if (heightString != null) {
            if (!heightString.equals(""))
                height = heightString.toDouble()

            //}
            else
                height = 0.0
            //if (weightString != null) {
            if (!weightString.equals(""))
                weight = weightString.toDouble()
            // }
            else
                weight = 0.0

            var bmiCount: Double = 0.0

            if (!(height > 220 || height < 140 || weight > 160.0 || weight < 40)) {
                if (europMesure) {
                    var bmiForKgCm: BmiForKgCm = BmiForKgCm(weight, height.toInt())
                    bmiCount = bmiForKgCm.countBmi() * 100
                } else {
                    var bmiForLbInch: BmiForLbInch = BmiForLbInch(weight, height.toInt())
                    bmiCount = bmiForLbInch.countBmi() * 100
                }
                bmiCount = bmiCount.toInt() * 1.0
                bmiCount = bmiCount / 100
            } else {
                if (height > 220 || height < 140) {
                    editText.error = "Height must be between 140 - 220"
                }
                if (weight > 160.0 || weight < 40) {
                    editText2.error = "Weight must be between 40 - 160"
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
//            R.string.
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
//                textView2.setTextColor(0xfffffff)
            }
            //bmiText = bmiCount.toString() + "\n" + bmiText
            if (bmiCount != 0.0) {
                textView2.text = bmiCount.toString()
                textView.text = bmiText
                when {
                    bmiCount <= 15 -> textView.setTextColor(resources.getColor(R.color.colorJakisMaloZdrowy))
                    bmiCount <= 18.5 -> textView.setTextColor(resources.getColor(R.color.colorJakisLapisLazuli))
                    bmiCount <= 25.5 -> textView.setTextColor(resources.getColor(R.color.colorJakisGrynszpan))
                    bmiCount <= 30.0 -> textView.setTextColor(resources.getColor(R.color.colorJakisRóżPompejański))
                    else -> textView.setTextColor(resources.getColor(R.color.colorJakiśGruby))
                }
            }
        }
        imageButton5.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("bmiValue",textView2.text.toString())
            intent.putExtra("bmiValueInfo",textView.text.toString())
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
//        super.onSaveInstanceState(outState)
//        outState!!.putString("Heigh", editText.text.toString())
        outState?.run {
            putString("Heigh", editText.text.toString())
            putString("Weigh", editText2.text.toString())
            putString("DoubleBmi", textView2.text.toString())
            putString("StringBmi", textView.text.toString())
            putString("StringBmicolor", textView.currentTextColor.toString())


        }
//        outState!!.putString("Weigh", editText2.text.toString())
//        outState!!.putString("DoubleBmi", editText2.text.toString())
//        outState!!.putString("StringBmi", editText2.text.toString())
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



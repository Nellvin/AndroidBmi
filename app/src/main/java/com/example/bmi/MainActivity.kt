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
    val hisManag = HistoryManager()
    var prefs: SharedPreferences? = null
    var height: Double = 0.0
    var heightString: String = ""
    var weight: Double = 0.0
    var weightString: String = ""

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

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Hisotry recovery
        prefs = this.getSharedPreferences("com.example.bmi.prefs", 0)
        val stringss = prefs!!.getString("HISTORY", "")
        Log.i("MainON_CREATE", stringss)

        hisManag.tooArrayList(stringss)


        button.setOnClickListener {
            calculateBMI()
        }
        imageButton5.setOnClickListener {
            openInfo()
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

//            val putExtra =
            intent.putExtra("arr", hisManag.getArray())
            startActivity(intent)
        }
        if (item.itemId == R.id.main_menu_changeMesures) {
            if (europMesure) {
                europMesure = false
            } else {
                textView3.setText(R.string.Heightcm)
                textView4.setText(R.string.Weightkg)
                europMesure = true
            }
            editText.setText("")
            editText2.setText("")
        }

        return super.onOptionsItemSelected(item)
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
        editor.putString("HISTORY", hisManag.toString())
        Log.i("maainDESTROY", hisManag.toString())
        editor.apply()

        super.onStop()
    }

    fun calculateBMI() {
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
            bmiText = getString(R.string.v_sev_under)
        if (bmiCount > 15 && bmiCount <= 16)
            bmiText = getString(R.string.sev_under)
        if (bmiCount > 16 && bmiCount <= 18.5)
            bmiText = getString(R.string.under)
        if (bmiCount > 18.5 && bmiCount <= 25)
            bmiText = getString(R.string.healthy)
        if (bmiCount > 25 && bmiCount <= 30)
            bmiText = getString(R.string.over)
        if (bmiCount > 30 && bmiCount <= 35)
            bmiText = getString(R.string.mod_obe)
        if (bmiCount > 35 && bmiCount <= 40)
            bmiText = getString(R.string.sev_obe)
        if (bmiCount > 40 && bmiCount <= 45)
            bmiText = getString(R.string.ver_sev_obe)
        if (bmiCount > 45 && bmiCount <= 50)
            bmiText = getString(R.string.mor_obe)
        if (bmiCount > 50 && bmiCount <= 60)
            bmiText = getString(R.string.sup_obes)
        if (bmiCount > 60) {
            bmiText = getString(R.string.hyp_obe)
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
                hisManag.addToArray(heightString + "|" + weightString + "|" + "kg/cm|" + bmiCount + "|" + Date().date + "." + (Date().month + 1) + "." + (Date().year + 1900) + "\n" + Date().hours + ":" + Date().minutes + ":" + Date().seconds + "|" + textView.currentTextColor)
            else
                hisManag.addToArray(heightString + "|" + weightString + "|" + "lb/inch |" + bmiCount + "|" + Date().date + "." + (Date().month + 1) + "." + (Date().year + 1900) + "\n" + Date().hours + ":" + Date().minutes + ":" + Date().seconds + "|" + textView.currentTextColor)
        }
    }
    fun openInfo(){
        val intent = Intent(this, InfoActivity::class.java)
        intent.putExtra("bmiValue", textView2.text.toString())
        intent.putExtra("bmiValueInfo", textView.text.toString())

        startActivity(intent)
    }

}




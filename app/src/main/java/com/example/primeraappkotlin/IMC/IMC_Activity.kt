package com.example.primeraappkotlin.IMC

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.primeraappkotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMC_Activity : AppCompatActivity() {
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 60
    private var currentAge: Int = 18
    private var currentHeight: Int = 100

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView

    private lateinit var textViewHeight:TextView
    private lateinit var rsHeight: RangeSlider

    private lateinit var btnSubtractWeight : FloatingActionButton
    private lateinit var btnPlusWeight : FloatingActionButton
    private lateinit var tvWeight : TextView

    private lateinit var btnSubtractAge : FloatingActionButton
    private lateinit var btnPlusAge : FloatingActionButton
    private lateinit var tvAge : TextView

    private lateinit var CalculateButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)
        initComponents()
        initListener()
        initUI()

    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)

        textViewHeight = findViewById(R.id.height)
        rsHeight = findViewById(R.id.rsHeight)

        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)

        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)

        CalculateButton = findViewById(R.id.CalculateButton)


    }

    private fun initListener(){
        viewMale.setOnClickListener {
            changeGender()
            setGender()
        }

        viewFemale.setOnClickListener {
            changeGender()
            setGender()
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            currentHeight = value.toInt()
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            textViewHeight.text = "$result cm"
        }

        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }

        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }

        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        CalculateButton.setOnClickListener {
            calculateIMC()
        }
    }
    private fun setWeight(){
        tvWeight.text = currentWeight.toString()
    }
    private fun setAge(){
        tvAge.text = currentAge.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }
    private fun setGender(){

        viewMale.setCardBackgroundColor(getBackGroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackGroundColor(isFemaleSelected))

    }

    private fun getBackGroundColor(isSelectedComponent:Boolean): Int{

        val ColorReference = if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }

        return ContextCompat.getColor(this,ColorReference)
    }

    private fun calculateIMC(){

        val df = DecimalFormat("#.##")
        val imc = currentWeight.toFloat()/(currentHeight.toFloat()/100 * currentHeight.toFloat() / 100)
        val resultImc = df.format(imc)

        AlertDialog.Builder(this)
            .setTitle("IMC")
            .setMessage("Tu IMC es: $resultImc")
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
    //Esta funcion setea los valores iniciales al iniciar la pantalla
    private fun initUI(){
        setGender()
        setWeight()
        setAge()
    }
}
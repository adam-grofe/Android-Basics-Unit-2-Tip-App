package com.example.android_unit_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_unit_2.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip(){
        val serviceAmt = binding.costOfService.text.toString().toDoubleOrNull()
        if( serviceAmt ==  null || serviceAmt == 0.0 ) {
            displayTip(0.0)
            return
        }

        val percent = when(binding.radioGroup.checkedRadioButtonId){
            R.id.amazing_button -> 0.25
            R.id.good_button -> 0.20
            else -> 0.15
        }
        var tip = serviceAmt * percent
        if( binding.roundSwitch.isChecked ){
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip( tip : Double ){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.textResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
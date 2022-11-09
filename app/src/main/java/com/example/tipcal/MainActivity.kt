package com.example.tipcal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.widget.ToolbarWidgetWrapper
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var seek: SeekBar
    lateinit var percent: TextView
    lateinit var tipView: TextView
    lateinit var totalAmtView: TextView
    lateinit var bill: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seek = findViewById(R.id.seekBar)
        percent = findViewById(R.id.per)
        tipView = findViewById(R.id.txtTip)
        totalAmtView = findViewById(R.id.txtTotal)
        bill = findViewById(R.id.etBase)

        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                percent.text = "$progress%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {

            }

            override fun onStopTrackingTouch(seek: SeekBar) {
            }
        })
        bill.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                computeTipAndTotal()
            }
        })

    }

    private fun computeTipAndTotal() {
        if (bill.text.isEmpty()){
            tipView.text=""
            totalAmtView.text=""
            return
        }
        val initialBill = bill.text.toString().toDouble()
        val tipPercent = seek.progress
        val tipAmt = initialBill * tipPercent / 100
        val totalAmt = initialBill + tipAmt
        tipView.text = "%.2f".format(tipAmt)
        totalAmtView.text = "%.2f".format(totalAmt)
    }
}

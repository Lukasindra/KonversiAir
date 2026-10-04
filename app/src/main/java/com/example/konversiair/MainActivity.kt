package com.example.konversiair

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val massaJenisAir = 1.0 // g/mL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rgDari = findViewById<RadioGroup>(R.id.rgDari)
        val rgKe = findViewById<RadioGroup>(R.id.rgKe)
        val etInput = findViewById<EditText>(R.id.etInput)
        val etHasil = findViewById<EditText>(R.id.etHasil)
        val btnHitung = findViewById<Button>(R.id.btnHitung)

        btnHitung.setOnClickListener {
            val nilai = etInput.text.toString().toDoubleOrNull()
            if (nilai == null) {
                etInput.error = "Isi angka dulu"
                return@setOnClickListener
            }
            val dariVolume = rgDari.checkedRadioButtonId == R.id.rbDariVolume
            val keVolume = rgKe.checkedRadioButtonId == R.id.rbKeVolume

            val hasil = when {
                dariVolume == keVolume -> nilai                // satuan sama
                dariVolume -> nilai * massaJenisAir            // mL -> gram
                else -> nilai / massaJenisAir                  // gram -> mL
            }
            etHasil.setText(hasil.toString())
        }
    }
}
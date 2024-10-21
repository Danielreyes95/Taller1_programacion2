package com.danielreyes.evaluacion_n1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val pastel = ItemMenu("pastel", 12000)
    val cazuela = ItemMenu("cazuela", 10000)
    val cuentaMesa = CuentaMesa(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etCantpastel = findViewById<EditText>(R.id.etCantpastel)
        val etCantcazuela = findViewById<EditText>(R.id.editTextNumber2)
        val switchPropina = findViewById<Switch>(R.id.switch1)
        val tvTotalPastel = findViewById<TextView>(R.id.tvTotalPastel)
        val tvTotalCazuela = findViewById<TextView>(R.id.tvtotalCazuela)
        val tvTotal = findViewById<TextView>(R.id.tvTotalapagar)
        val tvPropina = findViewById<TextView>(R.id.tvTotalPropina)
        val tvComida = findViewById<TextView>(R.id.tvTotalcomida)

        val textWatcherPastel = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                actualizarTotales()
            }
        }

        val textWatcherCazuela = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                actualizarTotales()
            }
        }

        etCantpastel.addTextChangedListener(textWatcherPastel)
        etCantcazuela.addTextChangedListener(textWatcherCazuela)
        switchPropina.setOnCheckedChangeListener { _, _ ->
            actualizarTotales()
        }
    }

    fun actualizarTotales() {
        val etCantpastel = findViewById<EditText>(R.id.etCantpastel)
        val etCantcazuela = findViewById<EditText>(R.id.editTextNumber2)
        val switchPropina = findViewById<Switch>(R.id.switch1)
        val tvTotalPastel = findViewById<TextView>(R.id.tvTotalPastel)
        val tvTotalCazuela = findViewById<TextView>(R.id.tvtotalCazuela)
        val tvTotal = findViewById<TextView>(R.id.tvTotalapagar)
        val tvPropina = findViewById<TextView>(R.id.tvTotalPropina)
        val tvComida = findViewById<TextView>(R.id.tvTotalcomida)

        val cantidadnumeropastel = etCantpastel.text.toString().toIntOrNull() ?: 0
        val cantidadnumerocazuela = etCantcazuela.text.toString().toIntOrNull() ?: 0

        val itemPastel = ItemMesa(pastel, cantidadnumeropastel)
        val itemCazuela = ItemMesa(cazuela, cantidadnumerocazuela)

        cuentaMesa.items.clear()
        cuentaMesa.agregarItem(itemPastel)
        cuentaMesa.agregarItem(itemCazuela)

        tvTotalPastel.text = formatCurrency(itemPastel.calcularSubtotal())
        tvTotalCazuela.text = formatCurrency(itemCazuela.calcularSubtotal())

        val totalSinPropina = cuentaMesa.calcularTotalSinPropina()
        val propina = if (switchPropina.isChecked) cuentaMesa.calcularPropina().toInt() else 0
        val totalConPropina = (totalSinPropina + propina)

        tvComida.text = formatCurrency(totalSinPropina)
        tvPropina.text = formatCurrency(propina)
        tvTotal.text = "${formatCurrency(totalConPropina)}"
    }

    fun formatCurrency(value: Int): String {
        return "$" + String.format("%,d", value).replace(',', '.')
    }
}

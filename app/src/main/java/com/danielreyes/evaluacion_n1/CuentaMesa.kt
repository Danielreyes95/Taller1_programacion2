package com.danielreyes.evaluacion_n1

class CuentaMesa(val mesa: Int) {
    val items = mutableListOf<ItemMesa>()
    var aceptarPropina = true

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        val agregandoItem = ItemMesa(itemMenu, cantidad)
        items.add(agregandoItem)
    }

    fun agregarItem(itemMesa: ItemMesa) {
        items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Int {
        var total = 0
        for (actual in items) {
            total += actual.calcularSubtotal()
        }
        return total
    }

    fun calcularPropina(): Double {
        return 0.1 * calcularTotalSinPropina()
    }

    fun calcularTotalConPropina(): Double {
        val totalSinPropina = calcularTotalSinPropina()
        val propina = calcularPropina()
        return totalSinPropina + propina
    }
}

package com.danielreyes.evaluacion_n1

class ItemMesa(val itemMenu: ItemMenu, var cantidad: Int){
    fun calcularSubtotal():Int{
        return itemMenu.precio * cantidad
    }
}

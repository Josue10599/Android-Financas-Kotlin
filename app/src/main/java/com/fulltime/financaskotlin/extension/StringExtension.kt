package com.fulltime.financaskotlin.extension

fun String.formataString(limiteCaracteres: Int): String {
    if (this.length > limiteCaracteres) {
        return "${this.substring(0, limiteCaracteres)}..."
    }
    return this
}
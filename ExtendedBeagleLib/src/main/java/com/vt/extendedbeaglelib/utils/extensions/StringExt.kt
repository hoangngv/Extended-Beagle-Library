package com.vt.extendedbeaglelib.utils.extensions

fun String.applyCurrencyFormat(currencySuffix: String): String {
    return "$this $currencySuffix"
}
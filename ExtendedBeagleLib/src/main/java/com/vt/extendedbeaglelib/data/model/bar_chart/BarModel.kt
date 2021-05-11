package com.vt.extendedbeaglelib.data.model.bar_chart

data class BarModel(
    val x: Float,
    val y: FloatArray,
    val icon: String? = null,
    val title: String,
    val color: List<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BarModel

        if (x != other.x) return false
        if (!y.contentEquals(other.y)) return false
        if (icon != other.icon) return false
        if (title != other.title) return false
        if (color != other.color) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.contentHashCode()
        result = 31 * result + (icon?.hashCode() ?: 0)
        result = 31 * result + title.hashCode()
        result = 31 * result + color.hashCode()
        return result
    }
}
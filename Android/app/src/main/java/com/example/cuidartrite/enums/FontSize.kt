package com.example.cuidartrite.enums

data class FontSize(
    val valor: Int,
    val descricao: String
) {
    companion object {
        val PEQUENO = FontSize(1, "Pequeno")
        val NORMAL = FontSize(2, "Normal")
        val GRANDE = FontSize(3, "Grande")
        val ENORME = FontSize(4, "Enorme")

        val lista = listOf(PEQUENO, NORMAL, GRANDE, ENORME)

        fun fromDescricao(descricao: String): FontSize? {
            return lista.find { it.descricao == descricao }
        }

        fun fromValor(valor: Int): FontSize? {
            return lista.find { it.valor == valor }
        }
    }

    override fun toString(): String = descricao
}

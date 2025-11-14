package com.example.cuidartrite.enums

enum class TechiniqueType(val id: Int, val title: String) {
    GuidedStretching(1, "Alongamentos Guiados"),
    BreathingTechnique(2, "Técnicas de Respiração");

    fun fromValor(id: Int): TechiniqueType? {
        return entries.find { it.id == id }
    }
}

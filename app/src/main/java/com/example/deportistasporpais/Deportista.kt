package com.example.deportistasporpais
import java.io.Serializable

data class Deportista(val name: String, val deporte: String, val enActividad: Boolean) : Serializable

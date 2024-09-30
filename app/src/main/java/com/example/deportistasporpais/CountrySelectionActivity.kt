package com.example.deportistasporpais
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class CountrySelectionActivity : AppCompatActivity() {

    private val deportistasMap = mapOf(
        "Argentina" to listOf(
            Deportista("Lionel Messi", "Fútbol", true),
            Deportista("Manu Ginóbili", "Basket", false),
            Deportista("Guillermo Vilas", "Tenis", false),
            Deportista("Luciana Aymar", "Hockey sobre césped", false),
            Deportista("Paula Pareto", "Judo", false),
            Deportista("Franco Colapinto", "Automovilismo", true),
            Deportista("Gabriela Sabatini", "Tenis", false),
            Deportista("Carlos Delfino", "Basket", true),
            Deportista("Diego Maradona", "Fútbol", false),
            Deportista("Raúl de la Cruz Chaparro", "Futbol", false)
        ),
        "Brasil" to listOf(
            Deportista("Pelé", "Fútbol", false),
            Deportista("Neymar Jr.", "Fútbol", true),
            Deportista("Gustavo Kuerten", "Tenis", false),
            Deportista("Marta Vieira da Silva", "Fútbol", true),
            Deportista("Ayrton Senna", "Automovilismo", false),
            Deportista("Oscar Schmidt", "Basket", false),
            Deportista("Gabriel Medina", "Surf", true),
            Deportista("Thiago Silva", "Fútbol", true),
            Deportista("Zico", "Fútbol", false),
            Deportista("Ronaldo", "Fútbol", false)
        ),
        "Colombia" to listOf(
            Deportista("James Rodríguez", "Fútbol", true),
            Deportista("Caterine Ibargüen", "Atletismo", false),
            Deportista("Nairo Quintana", "Ciclismo", true),
            Deportista("Radamel Falcao", "Fútbol", true),
            Deportista("Mariana Pajón", "BMX", true),
            Deportista("Juan Pablo Montoya", "Automovilismo", false),
            Deportista("Fernando Gaviria", "Ciclismo", true),
            Deportista("Carlos Valderrama", "Fútbol", false),
            Deportista("Rigoberto Urán", "Ciclismo", true),
            Deportista("Yuberjen Martínez", "Boxeo", true)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_selection)

        val countries = intent.getStringArrayListExtra("countries") ?: return
        val spinner = findViewById<Spinner>(R.id.country_spinner)
        val listView = findViewById<ListView>(R.id.deportistas_list)

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCountry = countries[position]
                val deportistas = deportistasMap[selectedCountry] ?: emptyList()

                val adapter = ArrayAdapter(this@CountrySelectionActivity, android.R.layout.simple_list_item_1, deportistas.map { it.name })
                listView.adapter = adapter

                listView.setOnItemClickListener { _, _, index, _ ->
                    val deportista = deportistas[index]

                    // Pasar el objeto Deportista a la siguiente actividad
                    val intent = Intent(this@CountrySelectionActivity, DeportistaDetailActivity::class.java)
                    intent.putExtra("deportista", deportista)
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}

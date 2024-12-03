package com.example.vidalexaokapplication

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider


/* Las cosas en las que he fallado: en el segundo TextEdit tenia que haber hecho un AppCompatEditText pero me di cuanta demasiado
* tarde, Tampoco he logrado pasar la imagen de una pantalla a otra y he tenido problemas con el putExtras por eso lo he dejado en blanco*/
class VidalMainActivity : AppCompatActivity() {

    private lateinit var viewCard1: CardView
    private lateinit var viewCard2: CardView
    private lateinit var viewCard3: CardView
    private lateinit var tvRan: RangeSlider
    private lateinit var tvnumRange: TextView
    private lateinit var tvname: EditText
    private lateinit var chekk: FloatingActionButton

    private var bolCard1 = false
    private var bolCard2 = false
    private var bolCard3 = true
    private var name = "pepe"
    private var poder = "50"
    private var all = "todo"
    private var raza = ""

    companion object{
        const val Extra = "all"
    }

    private fun initComponents() {
        viewCard1 = findViewById(R.id.card1)
        viewCard2 = findViewById(R.id.card2)
        viewCard3 = findViewById(R.id.card3)
        tvRan = findViewById(R.id.tvRange)
        tvnumRange = findViewById(R.id.numRange)
        tvname = findViewById(R.id.charname)
        chekk = findViewById(R.id.fab)

    }

    private fun initListeners(){
        viewCard1.setOnClickListener{
            bolCard1 = true
            bolCard2 = false
            bolCard3 = false
            cambiarColor()
            raza = "Yuanti"
        }
        viewCard2.setOnClickListener{
            bolCard1 = false
            bolCard2 = true
            bolCard3 = false
            cambiarColor()
            raza = "Orco"
        }
        viewCard3.setOnClickListener{
            bolCard1 = false
            bolCard2 = false
            bolCard3 = true
            cambiarColor()
            raza = "Tiefling"
        }
        tvRan.addOnChangeListener{_, value,_ ->
            tvnumRange.text = DecimalFormat("#.##").format(value)
            poder = tvnumRange.text.toString()
        }
        tvname.setOnClickListener{
            name = tvname.text.toString()
        }
        chekk.setOnClickListener{
            if(tvname.text.isEmpty()){
                Toast.makeText(this, "No Hay Nombre", Toast.LENGTH_LONG).show()
            }else{
                val intentGA = Intent(this,VidalResultActivity::class.java)
                //intentGA.putExtra("Extra", todo())
                startActivity(intentGA)
            }
        }

    }
    private fun todo(){
        all = " El Personaje $name es de la Raza $raza y tiene una fuerza de $poder "

    }
    private fun cambiarColor(){
        if(bolCard1){
            viewCard1.setCardBackgroundColor(getColor(R.color.tarsi))
            viewCard2.setCardBackgroundColor(getColor(R.color.tarno))
            viewCard3.setCardBackgroundColor(getColor(R.color.tarno))
        }else if(bolCard2){
            viewCard1.setCardBackgroundColor(getColor(R.color.tarno))
            viewCard2.setCardBackgroundColor(getColor(R.color.tarsi))
            viewCard3.setCardBackgroundColor(getColor(R.color.tarno))

        }else{
            viewCard1.setCardBackgroundColor(getColor(R.color.tarno))
            viewCard2.setCardBackgroundColor(getColor(R.color.tarno))
            viewCard3.setCardBackgroundColor(getColor(R.color.tarsi))
        }

    }

    private fun initUI(){
        bolCard1 = false
        bolCard2 = false
        bolCard3 = true
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_main)

        initComponents()
        initListeners()
        initUI()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
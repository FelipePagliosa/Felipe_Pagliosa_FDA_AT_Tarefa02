package br.pagliosa.felipe_pagliosa_fda_at_tarefa02

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var carta: ImageView? =null

    var cartas= mutableListOf(
        R.drawable.carta_as,R.drawable.carta_coringa,R.drawable.carta_coringa)

    fun selecionar(view: View){
        when(view){
            imageViewCarta1->{
                carta=imageViewCarta1
                imageViewCarta1.alpha=1f
                imageViewCarta2.alpha=0.6f
                imageViewCarta3.alpha=0.6f

            }
            imageViewCarta2->{
                carta=imageViewCarta2
                imageViewCarta1.alpha=0.6f
                imageViewCarta2.alpha=1f
                imageViewCarta3.alpha=0.6f

            }
            imageViewCarta3->{
                carta=imageViewCarta3
                imageViewCarta1.alpha=0.6f
                imageViewCarta2.alpha=0.6f
                imageViewCarta3.alpha=1f
            }

        }
    }

    fun confirmar(view: View){
        if(carta!=null){

            buttonConfirma.isEnabled=false
            imageViewCarta1.isClickable=false
            imageViewCarta2.isClickable=false
            imageViewCarta3.isClickable=false

            var cartaEscolhida= cartas.random()
            var mediaPlayer=MediaPlayer.create(this,R.raw.card_flip)
            mediaPlayer.start()
            carta!!.animate().rotationYBy(90f).setDuration(350).withEndAction(){
                carta!!.setImageResource(cartaEscolhida)
                carta!!.animate().rotationYBy(90f).setDuration(350).withEndAction(){
                    if(cartaEscolhida==R.drawable.carta_as){
                        carta!!.tag = "R.drawable.carta_as"
                    }
                    else{
                        carta!!.tag = "R.drawable.carta_coringa"
                    }

                    when(carta!!.tag){
                        "R.drawable.carta_as"->{
                            textViewResultado.text = "Você Ganhou!!"
                            var mediaPlayer=MediaPlayer.create(this,R.raw.tada_sucess_soundbible)
                            mediaPlayer.start()
                        }
                        "R.drawable.carta_coringa"->{
                            textViewResultado.text ="Você Perdeu!!"
                            var mediaPlayer=MediaPlayer.create(this,R.raw.sad_trombone_soundbible)
                            mediaPlayer.start()
                        }
                    }
                    textViewResultado.isVisible = true
                    buttonTentarNovamente.isEnabled=true
                    buttonTentarNovamente.isVisible=true
                }
            }
        }
        else{
            Toast.makeText(baseContext,"Selecione uma carta!",Toast.LENGTH_LONG).show()
        }
    }

    fun tentarNovamente(view:View){
        buttonTentarNovamente.isEnabled=false
        buttonTentarNovamente.isVisible=false
        textViewResultado.isVisible=false
        var mediaPlayer=MediaPlayer.create(this,R.raw.card_flip)
        mediaPlayer.start()
        carta!!.animate().rotationYBy(90f).setDuration(350).withEndAction(){
            carta!!.setImageResource(R.drawable.carta_verso)
            carta!!.animate().rotationYBy(90f).setDuration(350).withEndAction(){
                buttonConfirma.isEnabled=true
                imageViewCarta1.isClickable=true
                imageViewCarta2.isClickable=true
                imageViewCarta3.isClickable=true
                imageViewCarta1.alpha=1f
                imageViewCarta2.alpha=1f
                imageViewCarta3.alpha=1f
                carta=null
            }
        }
    }
}

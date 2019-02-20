package com.pauenrech.apppizzeria


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import kotlinx.android.synthetic.main.show_image.*
import com.squareup.picasso.Picasso


class showImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_image)

        //Al final utilicé una libreria para el pinch-to-zoom ya que estaba perdiendo mucho tiempo intenado implementarlo
        //Yo mismo. Aun asi, espero poder implementar algo asi más adelante
        val inTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.change_image_from_activity_to_activity)

        window.sharedElementEnterTransition = inTransition

        val idImage = intent.getIntExtra("imageToResize",0)
        Picasso.get().load(idImage).into(imageDetail)

    }

    override fun onBackPressed() {
        supportFinishAfterTransition()

    }

}

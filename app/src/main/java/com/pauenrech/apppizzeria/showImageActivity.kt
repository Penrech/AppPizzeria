package com.pauenrech.apppizzeria


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.animation.Animation
import kotlinx.android.synthetic.main.show_image.*
import com.squareup.picasso.Picasso


class showImageActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_image)

        var inTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.change_image_from_activity_to_activity)

        window.sharedElementEnterTransition = inTransition

        val idImage = intent.getIntExtra("imageToResize",0)
        //imageDetail.setImageResource(idImage)
        Picasso.get().load(idImage).into(imageDetail)

        imageDetail.animate().setUpdateListener {
            Log.i("TAG","Imagen animada")
        }

    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
        /*if(imagenAmpliada){


        }
        else{
            supportFinishAfterTransition()
        }*/
    }

}

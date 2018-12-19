package com.pauenrech.apppizzeria

import android.animation.TimeInterpolator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import kotlinx.android.synthetic.main.show_image.*
import android.util.DisplayMetrics
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.roundToInt
import android.util.TypedValue
import android.view.animation.Interpolator


class showImageActivity : AppCompatActivity() {


    var scaleGestureDetector: ScaleGestureDetector? = null
    var imagenAmpliada = false
    var defaultTransition : Transition? = null
    var flingTransition: Transition? = null

    var fingers = 1
    var view: View? = null

    private lateinit var gestureDetector: GestureDetector

    var metrics = DisplayMetrics()
    var dpDensity: Float? = null
    var minMargyn: Int? = null
    var marginY: Int? = null
    var ratioX: Double? = null
    var ratioY: Double? = null
    var minScrollX: Int? = null
    var maxScrollX: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_image)
/*
        windowManager.defaultDisplay.getMetrics(metrics)
        dpDensity = metrics.density;

        imageDetail.setImageResource(intent.getIntExtra("imageToResize",0))

        ratioX = imageDetail.drawable.intrinsicHeight.toDouble() / imageDetail.drawable.intrinsicWidth.toDouble()
        ratioY = Math.pow(ratioX!!,-1.0)
        getMinImageHeight()
        minMargyn = marginY
        centerAndScaleImage(marginY!!)

        minScrollX = - pxToDp(imageDetail.drawable.intrinsicWidth)
        maxScrollX= - minScrollX!!
        Log.i("TAG","minimo scroll : $minScrollX")

        //scaleGestureDetector = ScaleGestureDetector(this,MiOnScaleGestureListener())
        gestureDetector = GestureDetector(this,GestureDetectorCostum())

        imageDetail.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(event)
                Log.i("TAG","numero de dedos: ${event.pointerCount}")
                view = v
                fingers = event.pointerCount
                return true
            }
        })

        defaultTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.resize_image)

        flingTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.resize_image)

*/
    }

    override fun onBackPressed() {
        if(imagenAmpliada){

            TransitionManager.beginDelayedTransition(imageBigLayout,defaultTransition)
/*
            centerAndScaleImage(marginY!!)
            imageDetail.scrollX = 0
            imageDetail.scrollY = 0
            imagenAmpliada = !imagenAmpliada*/

        }
        else{
            supportFinishAfterTransition()
        }
    }
/*
    fun getMinImageHeight(){
       // marginY = metrics.heightPixels - (((pxToDp(imageDetail.drawable.intrinsicHeight).toDouble() * metrics.widthPixels.toDouble()) / pxToDp(imageDetail.drawable.intrinsicWidth).toDouble())).roundToInt()
        marginY = metrics.heightPixels - (ratioX!! * metrics.widthPixels.toDouble()).roundToInt()
        marginY = marginY!! / 2
        Log.i("TAG","Margen: $marginY")
    }

    fun centerAndScaleImage(margin: Int){
        val set = ConstraintSet()
        set.clone(imageBigLayout)
        set.connect(R.id.imageDetail,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP,margin)
        set.connect(R.id.imageDetail,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM,margin)
        set.applyTo(imageBigLayout)
    }

    fun pxToDp(px: Int): Int{
        return Math.ceil(px / dpDensity?.toDouble()!!).toInt()
    }


    inner class GestureDetectorCostum: GestureDetector.SimpleOnGestureListener(), GestureDetector.OnDoubleTapListener{

        private val SWIPE_MIN_DISTANCE = 120
        private val SWIPE_THRESHOLD_VELOCITY = 5000

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }
/*
        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if(imagenAmpliada){

                flingTransition?.duration = (Math.abs(e1?.getX()!! - e2?.getX()!!) / velocityX).roundToLong()
                TransitionManager.beginDelayedTransition(imageBigLayout,flingTransition)
                Log.i("Velocidad","$velocityX")
                imageDetail.scrollX += ((e1?.getX()!! - e2?.getX()!!) + Math.abs(velocityX) * 0.01).roundToInt()
                imageDetail.scrollX = Math.max(minScrollX!!, Math.min(imageDetail.scrollX, maxScrollX!!))

            }
            return false
        }
*/
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (fingers > 1)
                Log.i("TAG","Demasiados dedos")
            else{
                if (!imagenAmpliada) {
                    if (distanceY > 0.0 && distanceX < 5.0 && distanceX > -5.0) {
                        supportFinishAfterTransition()
                    }
                }
                else{

                    imageDetail.scrollX += distanceX.toInt()
                    imageDetail.scrollX = Math.max(minScrollX!!, Math.min(imageDetail.scrollX, maxScrollX!!))
                    //imageDetail.scrollY += distanceY.toInt()
                }
            }
            Log.i("TAG","Posicion de scrollX en scroll: ${imageDetail.scrollX}")
            return false
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            Log.i("TAG","onDoubleTap")
            TransitionManager.beginDelayedTransition(imageBigLayout,defaultTransition)

            if(!imagenAmpliada) {

                marginY = 0
                var actualHeight = metrics.heightPixels - marginY!!
                var actualWidth = actualHeight * ratioY!!
                var minX = - (actualWidth / 2.0).roundToInt()
                var maxX = - minX
                var scrollXTranslateRatio = ((ratioY!! * actualHeight) / metrics.heightPixels).roundToInt()
                var scrollXTranslated = (e!!.x - (metrics.widthPixels / 2.0)) * scrollXTranslateRatio
                imageDetail.scrollX = scrollXTranslated.roundToInt()
                imageDetail.scrollX = Math.max(minX, Math.min(imageDetail.scrollX, maxX))
                imageDetail.scrollY = 0

                centerAndScaleImage(marginY!!)
                imagenAmpliada = !imagenAmpliada

            }
            else {
                imagenAmpliada = !imagenAmpliada

                centerAndScaleImage(minMargyn!!)
                imageDetail.scrollX = 0
                imageDetail.scrollY = 0
            }

            return false
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            return false
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return false
        }
    }
/*
    inner class MyGestureListener: GestureDetector.SimpleOnGestureListener(){


        override fun onDown(e: MotionEvent?): Boolean {
            Log.i("TAG","dedos: ${e!!.pointerCount}")
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {

            Log.i("TAG","No deberia entrar aqui, onDoubleTap")
            if(imagenAmpliada == 1.0) {

                imagenAmpliada *= 2.0
                imagenAmpliada = Math.max(1.0, Math.min(imagenAmpliada, 3.5))
            }

            else imagenAmpliada = 1.0

            TransitionManager.beginDelayedTransition(imageBigLayout,defaultTransition)

            imageDetail.pivotX = e!!.x
            imageDetail.pivotY = e!!.y

            imageDetail.scaleX = imagenAmpliada.toFloat()
            imageDetail.scaleY = imagenAmpliada.toFloat()
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            Log.i("TAG","Onfling")
            return true
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            /*Log.i("TAG","MotionEvent1: $e1")
            Log.i("TAG","MotionEvent2: $e2")
            Log.i("TAG","distanceX: $distanceX")
            Log.i("TAG","distanceY: $distanceY")*/
            if (imagenAmpliada == 1.0) {
                if (distanceY > 0.0 && distanceX < 5.0 && distanceX > -5.0) {
                    supportFinishAfterTransition()
                }
            }
            else{/*
                imageDetail.trans += distanceX
                imageDetail.pivotY = distanceY*/
            }
            return false

        }

    }*/
/*
    inner class MiOnScaleGestureListener: ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector?): Boolean {

            imagenAmpliada *= (detector?.scaleFactor!!)

            imagenAmpliada = Math.max(1.0,Math.min(imagenAmpliada,3.5))

            imageDetail.scaleX = imagenAmpliada.toFloat()
            imageDetail.scaleY = imagenAmpliada.toFloat()
            return true
        }

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            imageDetail.pivotX = detector!!.focusX
            imageDetail.pivotY = detector!!.focusY
            Log.i("TAG","factor de escala solo tocar: ${detector.scaleFactor}")
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
            super.onScaleEnd(detector)
        }
    }
*/

*/
}

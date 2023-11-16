package com.example.ar_intialsetup

import android.content.ContentValues.TAG
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ar_intialsetup.databinding.ActivityMainBinding
import com.google.android.filament.Box
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.ar.core.Anchor
import com.google.ar.core.Session
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.ArSceneView
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import java.util.concurrent.CompletableFuture


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var arFragment: ArFragment
    private val url = "https://firebasestorage.googleapis.com/v0/b/cooksy-16c86.appspot.com/o/model.glb?alt=media&token=6ef1ae1a-21b1-42e0-8cbe-36f76cb052db"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment)   as ArFragment

        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            spawnObject(hitResult.createAnchor() , Uri.parse(url))
        }


    }
    private fun spawnObject(anchor : Anchor , modelUri: Uri){
        val renderableSource = RenderableSource.builder()
            .setSource(this , modelUri , RenderableSource.SourceType.GLB)
            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
            .build()
        ModelRenderable.builder()
            .setSource(this , renderableSource)
            .setRegistryId(modelUri)
            .build()
            .thenAccept {
                addNodeToScene(anchor , it)
            }
            .exceptionally {
                Toast.makeText(this, "An Error Occuered", Toast.LENGTH_SHORT).show()
                null
            }

    }



    private fun addNodeToScene(anchor: Anchor, modelRenderable: ModelRenderable){
        val anchorNode = AnchorNode(anchor)
        TransformableNode(arFragment.transformationSystem).apply {
            renderable = modelRenderable
            setParent(anchorNode)

        }
        arFragment.arSceneView.scene.addChild(anchorNode)

    }

}
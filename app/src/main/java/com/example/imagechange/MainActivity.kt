package com.example.imagechange

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.imagechange.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Registers a photo picker activity launcher in single-select mode.
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                var bitmap = BitmapFactory.decodeStream(getBaseContext().getContentResolver().openInputStream(uri))
                Log.d("PhotoPicker", "Selected URI: $uri")
                binding.imageView.setImageBitmap(bitmap)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }


        binding.button.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))

        }
    }


}
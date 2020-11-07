package io.github.sahalnazar.pdfviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.sahalnazar.pdfviewer.Constants.FROM_ASSET
import io.github.sahalnazar.pdfviewer.Constants.FROM_INTERNET
import io.github.sahalnazar.pdfviewer.Constants.FROM_STORAGE
import io.github.sahalnazar.pdfviewer.Constants.TYPE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpListeners()

    }

    private fun setUpListeners() {
        btnFromAsset.setOnClickListener {
            val intent = Intent(this, PdfViewActivity::class.java)
            intent.putExtra(TYPE, FROM_ASSET)
            startActivity(intent)
        }

        btnFromStorage.setOnClickListener {
            val intent = Intent(this, PdfViewActivity::class.java)
            intent.putExtra(TYPE, FROM_STORAGE)
            startActivity(intent)
        }

        brnFromInternet.setOnClickListener {
            val intent = Intent(this, PdfViewActivity::class.java)
            intent.putExtra(TYPE, FROM_INTERNET)
            startActivity(intent)
        }
    }


}
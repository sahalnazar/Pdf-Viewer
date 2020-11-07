package io.github.sahalnazar.pdfviewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rajat.pdfviewer.PdfViewerActivity
import io.github.sahalnazar.pdfviewer.Constants.ASSET_PDF
import io.github.sahalnazar.pdfviewer.Constants.FROM_ASSET
import io.github.sahalnazar.pdfviewer.Constants.FROM_INTERNET
import io.github.sahalnazar.pdfviewer.Constants.FROM_STORAGE
import io.github.sahalnazar.pdfviewer.Constants.INTERNET_PDF
import io.github.sahalnazar.pdfviewer.Constants.PDF_SELECTION_REQUEST_CODE
import io.github.sahalnazar.pdfviewer.Constants.TYPE
import kotlinx.android.synthetic.main.pdf_view_layout.*

@Suppress("SameParameterValue")
class PdfViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdf_view_layout)

        checkPdfAction(intent)

    }

    /**
     * com.github.barteksc:android-pdf-viewer
     */
    private fun loadPdfFromAssets(pdfName: String) {
        pdfView.fromAsset(pdfName)
            .password(null)
            .defaultPage(0)
            .onPageError { page, t ->
                Toast.makeText(this, "Couldn't load page: $page ($t)", Toast.LENGTH_LONG).show()
            }.load()
    }

    private fun loadPdfFromStorage() {
        Toast.makeText(this, "Choose PDF", Toast.LENGTH_SHORT).show()
        val storage = Intent(Intent.ACTION_GET_CONTENT)
        storage.type = "application/pdf"
        storage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(storage, "Choose PDF"), PDF_SELECTION_REQUEST_CODE
        )
    }

    /**
     * com.github.barteksc:android-pdf-viewer
     */
    private fun loadPdfFromUri(uri: Uri?) {
        pdfView.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PDF_SELECTION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val pdfSelected = data.data
            loadPdfFromUri(pdfSelected)
        } else {
            finish()
        }
    }

    /**
     * com.github.afreakyelf:Pdf-Viewer
     */
    private fun downloadPdfFromInternet() {

        startActivity(
            PdfViewerActivity.buildIntent(
                this,
                INTERNET_PDF,                      // PDF URL in String format
                false,
                "Pdf title/name ",         // PDF Name/Title in String format
                "",                  // If nothing specific, Put "" it will save to Downloads
                enableDownload = true              // This param is true by default.
            )
        )

    }


    private fun checkPdfAction(intent: Intent) {
        when (intent.getStringExtra(TYPE)) {
            FROM_ASSET -> {
                loadPdfFromAssets(ASSET_PDF)
            }
            FROM_STORAGE -> {
                loadPdfFromStorage()
            }
            FROM_INTERNET -> {
                downloadPdfFromInternet()
            }

        }
    }


}
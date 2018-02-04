package com.deveshshetty.jesuits

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import android.util.Log
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.shockwave.pdfium.PdfDocument.Meta

class PdfViewActivity : AppCompatActivity(), OnPageChangeListener {

    companion object {
        const val ARG_ASSET_FILE_NAME = "arg_pdf_asset_file_name"
        private const val ARG_PAGE_NUMBER = "arg_page_number"
    }

    private lateinit var pdfView: PDFView
    private var pageNumber = 0
    private var pdfName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        pageNumber = savedInstanceState?.getInt(ARG_PAGE_NUMBER) ?: 0

        intent.getStringExtra(ARG_ASSET_FILE_NAME)?.let {
            pdfName = it
            pdfView = findViewById<PDFView>(R.id.pdfView).apply {
                        recycle()
                        fromAsset(it)
                        .defaultPage(pageNumber)
                        .onPageChange(this@PdfViewActivity)
                        .enableAnnotationRendering(true)
                        .scrollHandle(DefaultScrollHandle(this@PdfViewActivity))
                        .load()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(ARG_PAGE_NUMBER, pageNumber)
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
        title = String.format("%s %s / %s", pdfName, page + 1, pageCount)
    }
}

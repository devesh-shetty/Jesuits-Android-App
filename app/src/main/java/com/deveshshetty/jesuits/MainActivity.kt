package com.deveshshetty.jesuits

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCardViewClicked(view: View) {
        val pdfName = when (view.id) {
            R.id.cv_spiritual -> "The spiritual exercises of st ignatius-ganss.pdf"
            R.id.cv_constitution -> "CONSTITUTIONS and Complementary Norms-NNCC_Eng.pdf"
            R.id.cv_saints -> "BOOK ON JESUIT SAINTS WITH SCRIPTURE READINGS-Errol Fernandes.pdf"
            R.id.cv_pilgrim -> "Autobiography-A Pilgrim’s Testament.pdf"
            else -> ""
        }

        if (pdfName.isNotEmpty()) {
            val intent = Intent(this, PdfViewActivity::class.java).apply {
                putExtra(PdfViewActivity.ARG_ASSET_FILE_NAME, pdfName)
            }
            startActivity(intent)
        }
    }

}

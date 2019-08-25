package id.haadii.submissiondicoding.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import id.haadii.submissiondicoding.R
import id.haadii.submissiondicoding.data.Book
import id.haadii.submissiondicoding.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel : DetailViewModel
    private lateinit var fragbinding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragbinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        val book = intent?.extras?.get("books") as Book

        Glide.with(this)
            .load(book.cover)
            .into(imageDetil)

        setSupportActionBar(toolbarDetailPublikasi)
        supportActionBar?.run {
            title = book.title
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        detailViewModel.setItem(book)

        fragbinding.viewmodel = detailViewModel
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

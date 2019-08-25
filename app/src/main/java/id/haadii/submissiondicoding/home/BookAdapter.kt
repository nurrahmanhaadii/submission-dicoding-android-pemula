package id.haadii.submissiondicoding.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.haadii.submissiondicoding.R
import id.haadii.submissiondicoding.data.Book
import kotlinx.android.synthetic.main.item_test.view.*

class BookAdapter(private val books: ArrayList<Book>, private val listener: (Book) -> Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
        return BookViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindBook(books[position], listener)
    }


    inner class BookViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val tvJudul = item.textView_item
        private val tvRilis = item.textView_rilis
        private val ivImage = item.iv_image


        fun bindBook(book: Book, listener: (Book) -> Unit) {
            tvJudul.text = book.title
            tvRilis.text = book.tglRilis

            Glide.with(itemView)
                .load(book.cover)
                .into(ivImage)

            itemView.setOnClickListener {
                listener(book)
            }
        }
    }
}
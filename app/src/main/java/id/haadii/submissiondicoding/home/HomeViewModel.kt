package id.haadii.submissiondicoding.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import id.haadii.submissiondicoding.data.Book
import id.haadii.submissiondicoding.data.repository.BookItemListener
import id.haadii.submissiondicoding.data.repository.FirebaseRepository

class HomeViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    val bookList = ArrayList<Book>()

    val onBookListChangeObserver = MutableLiveData<ArrayList<Book>>()

    fun getBookItem() {
        repository.getBook(object : BookItemListener {
            override fun onBookItemListener(book: Book) {
                bookList.add(book)
                onBookListChangeObserver.postValue(bookList)
            }
        })
    }
}

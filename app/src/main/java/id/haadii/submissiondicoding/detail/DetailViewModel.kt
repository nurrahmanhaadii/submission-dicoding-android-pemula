package id.haadii.submissiondicoding.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.haadii.submissiondicoding.data.Book

class DetailViewModel : ViewModel() {

    val title = MutableLiveData<String>()

    val katalog = MutableLiveData<String>()

    val publikasi = MutableLiveData<String>()

    val issn = MutableLiveData<String>()

    val rilis = MutableLiveData<String>()

    val deskripsi = MutableLiveData<String>()

    fun setItem(book: Book) {
        title.value = book.title
        katalog.value = book.noKatalog
        publikasi.value = book.noPublikasi
        issn.value = book.issn
        rilis.value = book.tglRilis
        deskripsi.value = book.deskripsi
    }
}
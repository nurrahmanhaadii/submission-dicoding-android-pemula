package id.haadii.submissiondicoding.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import id.haadii.submissiondicoding.data.Book

class FirebaseRepository {

    fun getCurrentUser() : String {
        return FirebaseAuth.getInstance().currentUser?.uid.toString()
    }

    fun getBook(callback: BookItemListener) {
        FirebaseFirestore.getInstance().collection("book")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    val title = document["judul"].toString()
                    val noPublikasi = document["no_publikasi"].toString()
                    val noKatalog = document["no_katalog"].toString()
                    val issn = document["issn_isbn"].toString()
                    val tglRilis = document["tgl_rilis"].toString()
                    val deskripsi = document["deskripsi"].toString()
                    val cover = document["cover"].toString()

                    val bookItem = Book(id = document.id, title = title, noKatalog = noKatalog, noPublikasi = noPublikasi, issn = issn,
                        tglRilis = tglRilis, deskripsi = deskripsi, cover = cover)

                    callback.onBookItemListener(bookItem)
                }
            }
            .addOnFailureListener {
                Log.e("onfailure", "exception: $it")
            }

    }
}

interface BookItemListener {
    fun onBookItemListener(book: Book)
}
package id.haadii.submissiondicoding.data

import java.io.Serializable

data class Book(
    val id : String,
    val title: String,
    val noPublikasi: String,
    val noKatalog: String,
    val issn: String,
    val tglRilis: String,
    val deskripsi: String,
    val cover: String
) : Serializable
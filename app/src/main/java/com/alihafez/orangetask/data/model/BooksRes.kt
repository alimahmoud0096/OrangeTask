package com.alihafez.orangetask.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class BooksRes(
    val kind: String? = null,
    val totalItems: Int? = null,
    val items: List<BookItem>? = null,
    val error: ErrorModel? = null
)

data class ErrorModel(
    val code: Int,
    val message: String
)

@Parcelize
data class BookItem(
    val volumeInfo: BookVolumeData,
) : Parcelable

@Parcelize
data class BookVolumeData(
    val title: String?,
    val description: String?,
    val publishedDate: String?,
    val imageLinks: BookImageData?,
    val authors: List<String>? = arrayListOf(),

    ) : Parcelable

@Parcelize
data class BookImageData(
    val smallThumbnail: String?,
    val thumbnail: String?,


    ) : Parcelable


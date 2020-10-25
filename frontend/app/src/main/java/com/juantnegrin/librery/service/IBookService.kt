package com.juantnegrin.librery.service

import android.content.Context
import com.juantnegrin.librery.models.Book
import org.json.JSONArray
import org.json.JSONObject

interface IBookService {
    fun getAll(context: Context, completionHandler: (response: ArrayList<Book>?) -> Unit)

    fun getById(context: Context, bookId: Int, completionHandler: (response: Book?) -> Unit)

    fun deleteById(context: Context, bookId: Int, completionHandler: () -> Unit)

    fun updateBook(context: Context, book: Book, completionHandler: () -> Unit)

    fun createBook(context: Context, book: Book, completionHandler: () -> Unit)
}
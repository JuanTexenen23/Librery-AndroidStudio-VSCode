package com.juantnegrin.librery.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.juantnegrin.librery.models.Book
import org.json.JSONObject

class BookServiceImpl : IBookService {
    override fun getAll(context: Context, completionHandler: (response: ArrayList<Book>?) -> Unit) {
        val path = BookSingleton.getInstance(context).baseUrl + "/api/bicycles"
        val arrayRequest = JsonArrayRequest(Request.Method.GET, path, null,
                { response ->
                    var books: ArrayList<Book> = ArrayList()
                    for (i in 0 until response.length()) {
                        val book = response.getJSONObject(i)
                        val id = book.getInt("id")
                        val author = book.getString("author")
                        val title = book.getString("title")
                        val category = book.getString("category")
                        val descri = book.getString("descri")
                        books.add(Book(id, author, title,category,descri))
                    }
                    completionHandler(books)
                },
                { error ->
                    completionHandler(ArrayList<Book>())
                })
        BookSingleton.getInstance(context).addToRequestQueue(arrayRequest)
    }

    override fun getById(context: Context, bookId: Int, completionHandler: (response: Book?) -> Unit) {
        val path = BookSingleton.getInstance(context).baseUrl + "/api/book/" + bookId
        val objectRequest = JsonObjectRequest(Request.Method.GET, path, null,
                { response ->
                    if(response == null) completionHandler(null)

                    val id = response.getInt("id")
                    val author = response.getString("author")
                    val title = response.getString("title")
                    val category = response.getString("category")
                    val descri = response.getString("descri")

                    val book = Book(id, author, title,category,descri)
                    completionHandler(book)
                },
                { error ->
                    completionHandler(null)
                })
        BookSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun deleteById(context: Context, bookId: Int, completionHandler: () -> Unit) {
        val path = BookSingleton.getInstance(context).baseUrl + "/api/book/" + bookId
        val objectRequest = JsonObjectRequest(Request.Method.DELETE, path, null,
                { response ->
                    Log.v("Hola caracola", "se borró")
                    completionHandler()
                },
                { error ->
                    Log.v("Hola caracola", "dió error")
                    completionHandler()
                })
        BookSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun updateBook(context: Context, book: Book, completionHandler: () -> Unit) {
        val path = BookSingleton.getInstance(context).baseUrl + "/api/book/" + book.id
        val bookJson: JSONObject = JSONObject()
        bookJson.put("id", book.id.toString())
        bookJson.put("author", book.author)
        bookJson.put("title", book.title)
        bookJson.put("category", book.category)
        bookJson.put("descri", book.descri)

        val objectRequest = JsonObjectRequest(Request.Method.PUT, path, bookJson,
                { response ->
                    completionHandler()
                },
                { error ->
                    completionHandler()
                })
        BookSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun createBook(context: Context, book: Book, completionHandler: () -> Unit) {
        val path = BookSingleton.getInstance(context).baseUrl + "/api/book"
        val bookJson: JSONObject = JSONObject()
        bookJson.put("id", book.id.toString())
        bookJson.put("author", book.author)
        bookJson.put("title", book.title)
        bookJson.put("category", book.category)
        bookJson.put("descri", book.descri)

        val objectRequest = JsonObjectRequest(Request.Method.POST, path, bookJson,
                { response -> completionHandler() },
                { error -> completionHandler() })
        BookSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}
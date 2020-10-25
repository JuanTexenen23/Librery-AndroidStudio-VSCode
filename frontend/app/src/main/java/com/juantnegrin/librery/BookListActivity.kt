package com.juantnegrin.librery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.juantnegrin.librery.models.Book
import org.json.JSONException

class BookListActivity : AppCompatActivity() {
    private lateinit var books: ArrayList<Book>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BookAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        requestQueue = Volley.newRequestQueue(this)

        books = ArrayList<Book>()

        viewManager = LinearLayoutManager(this)

        viewAdapter = BookAdapter(books, this)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewBook)
        // use a linear layout manager
        recyclerView.layoutManager = viewManager

        // specify an viewAdapter (see also next example)
        recyclerView.adapter = viewAdapter

        getAllBook()

    }

    private fun getAllBook() {

        val url = "http://192.168.56.01:8080/api/book"
        val request =
                JsonArrayRequest(Request.Method.GET, url, null, { response ->
                    try {
                        Log.v("hola caracola", response.toString())
                        for (i in 0 until response.length()) {
                            val book = response.getJSONObject(i)
                            val id = book.getInt("id")
                            val author = book.getString("author")
                            val title = book.getString("title")
                            val category = book.getString("category")
                            val descri = book.getString("descri")
                            books.add(Book(id, author, title, category, descri))
                            Log.v("hola caracola", books.get(i).id.toString())
                        }
                        viewAdapter.bookList = books
                        viewAdapter.notifyDataSetChanged()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}
package com.juantnegrin.librery

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juantnegrin.librery.models.Book

class BookAdapter (var bookList: ArrayList<Book>, val context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.book_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(bookList[position], context)
    }

    override fun getItemCount(): Int {
        Log.v("hola caraoola", "kk")
        Log.v("hola caracola", bookList.size.toString())
        return bookList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(b: Book, context: Context){
            val url = "http://192.168.56.1:8080/img/book-"
            val txt_author: TextView = itemView.findViewById(R.id.textViewAuthor)
            val txt_title: TextView = itemView.findViewById(R.id.textViewTitle)
            val txt_category: TextView = itemView.findViewById(R.id.textViewCategory)
            val txt_descri: TextView = itemView.findViewById(R.id.textViewDescri)

            txt_author.text = b.author
            txt_title.text = b.title
            txt_category.text = b.category
            txt_descri.text = b.descri

        }
    }
}
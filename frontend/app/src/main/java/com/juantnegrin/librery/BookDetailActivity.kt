package com.juantnegrin.librery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.juantnegrin.librery.service.BookServiceImpl
import com.juantnegrin.librery.service.BookSingleton
import com.google.android.material.textfield.TextInputEditText
import com.juantnegrin.librery.models.Book
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {
    private lateinit var state: String
    private lateinit var TextInputEditTextAuthor: EditText
    private lateinit var TextInputEditTextTitle: EditText
    private lateinit var TextInputEditTextCategory: EditText
    private lateinit var TextInputEditTextDescri: EditText
    private lateinit var buttonEdit: Button
    private lateinit var buttonDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        state = this.intent.getStringExtra("state").toString()

        val bookId = this.intent.getIntExtra("bookId", 1)

        TextInputEditTextAuthor = findViewById(R.id.textInputEditTextAuthor)
        TextInputEditTextTitle = findViewById(R.id.textInputEditTextTitle)
        TextInputEditTextCategory = findViewById(R.id.textInputEditTextCategory)
        TextInputEditTextDescri = findViewById(R.id.textInputEditTextDescri)

        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            deleteBook(bookId)
        }

        if(state == "Showing") getBook(bookId)

        buttonEdit = findViewById(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            when(state){
                "Showing" -> {
                    changeButtonsToEditing()
                }
                "Editing" -> {
                    val book = Book(bookId, TextInputEditTextAuthor.text.toString(), TextInputEditTextTitle.text.toString(),
                            TextInputEditTextCategory.text.toString(), TextInputEditTextDescri.text.toString())
                    updateBook(book)
                }
                "Adding" -> {
                    val book = Book(bookId, TextInputEditTextAuthor.text.toString(), TextInputEditTextTitle.text.toString(),
                            TextInputEditTextCategory.text.toString(), TextInputEditTextDescri.text.toString())
                    createBook(book)
                }
            }
        }

        if(state == "Adding") changeButtonsToAdding()
    }

    private fun updateBook(book: Book) {
        val bookServiceImpl = BookServiceImpl()
        bookServiceImpl.updateBook(this, book) { ->
            run {
                changeButtonsToShowing(book.id)
                val intent = Intent(this, BookListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun createBook(book: Book) {
        val bookServiceImpl = BookServiceImpl()
        bookServiceImpl.createBook(this, book) { ->
            run {
                changeButtonsToShowing(book.id)
                val intent = Intent(this, BookListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun changeButtonsToAdding() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Add Book")
        TextInputEditTextAuthor.isEnabled = false
        TextInputEditTextTitle.isEnabled = false
        TextInputEditTextCategory.isEnabled = false
        TextInputEditTextDescri.isEnabled = false
        state = "Adding"
    }

    private fun changeButtonsToShowing(bookId: Int){
        buttonDelete.visibility = View.VISIBLE
        buttonDelete.isEnabled = true
        buttonEdit.setText("Edit Book")
        TextInputEditTextAuthor.isEnabled = false
        TextInputEditTextTitle.isEnabled = false
        TextInputEditTextCategory.isEnabled = false
        TextInputEditTextDescri.isEnabled = false
        state = "Showing"
    }

    private fun changeButtonsToEditing() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Apply changes")
        TextInputEditTextAuthor.isEnabled = false
        TextInputEditTextTitle.isEnabled = false
        TextInputEditTextCategory.isEnabled = false
        TextInputEditTextDescri.isEnabled = false
        state = "Editing"
    }

    private fun getBook(bookId: Int) {
        val bookServiceImpl = BookServiceImpl()
        bookServiceImpl.getById(this, bookId) { response ->
            run {

                val txt_author: TextInputEditText = findViewById(R.id.textInputEditTextAuthor)
                val txt_title: TextInputEditText = findViewById(R.id.textInputEditTextCategory)
                val txt_category: TextInputEditText = findViewById(R.id.textInputEditTextCategory)
                val txt_descri: TextInputEditText = findViewById(R.id.textInputEditTextDescri)
                val img: ImageView = findViewById(R.id.imageViewBookDetail)

                txt_author.setText(response?.author ?: "")
                txt_title.setText(response?.title ?: "")
                txt_category.setText(response?.category ?: "")
                txt_descri.setText(response?.descri ?: "")

                val url = BookSingleton.getInstance(this).baseUrl + "/img/book-"
                val imageUrl = url + (response?.id.toString() ?: "0" ) + ".jpg"
                Picasso.with(this).load(imageUrl).into(img);
            }
        }
    }

    private fun deleteBook(bookId: Int) {
        val bokServiceImpl = BookServiceImpl()
        bokServiceImpl.deleteById(this, bookId) { ->
            run {
                val intent = Intent(this, BookListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
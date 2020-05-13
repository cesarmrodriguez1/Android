package com.example.retrofitexample

import java.util.*

object SampleData {

    val books = ArrayList<Book>()

    private var COUNT = 5

    private var description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan quis justo quis hendrerit. Curabitur a ante neque. Fusce nec mauris sodales, auctor sem at, luctus eros. Praesent aliquam nibh neque. Duis ut suscipit justo, id consectetur orci. Curabitur ultricies nunc eu enim dignissim, sed laoreet odio blandit."

    init {
        // Add some sample items
        val book1 = Book()
        book1.isbn = 1
        book1.name = "Book 1"
        book1.description = description
        book1.editorial = "Old Classic"
        books.add(book1)

        val book2 = Book()
        book2.isbn = 2
        book2.name = "Book 2"
        book2.description = description
        book2.editorial = "Editorial 2"
        books.add(book2)

        val book3 = Book()
        book3.isbn = 3
        book3.name = "Book 3"
        book3.description = description
        book3.editorial = "Editorial 3"
        books.add(book3)

        val book4 = Book()
        book4.isbn = 4
        book4.name = "Book 4"
        book4.description = description
        book4.editorial = "Editorial 4"
        books.add(book4)

        val book5 = Book()
        book5.isbn = 5
        book5.name = "Book 5"
        book5.description = description
        book5.editorial = "Editorial 5"
        books.add(book5)


    }

    fun addBook(item: Book) {
        item.isbn = COUNT
        books.add(item)
        COUNT += 1
    }

    fun getBookByIsbn(isbn: Int): Book? {
        for (i in books.indices) {
            if (books[i].isbn == isbn) {
                return books[i]
            }
        }

        return null
    }

    fun deleteDestination(isbn: Int) {
        var removingBook: Book? = null

        for (i in books.indices) {
            if (books[i].isbn == isbn) {
                removingBook = books[i]
            }
        }

        if (removingBook != null) {
            books.remove(removingBook)
        }
    }

    fun updateBook(book: Book) {
        for (i in books.indices) {
            if (books[i].isbn == book.isbn) {
                val destinationToUpdate = books[i]

                destinationToUpdate.name = book.name
                destinationToUpdate.description = book.description
                destinationToUpdate.editorial = book.editorial
            }
        }
    }
}

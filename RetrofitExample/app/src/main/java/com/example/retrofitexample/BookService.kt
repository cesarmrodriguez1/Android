package com.example.retrofitexample

import retrofit2.Call
import retrofit2.http.*

interface BookService {

    @GET("book")
    fun getBooksList(): Call<List<Book>>

    @GET("book/{isbn}")
    fun getBook(@Path("isbn") isbn:Int): Call<Book>

    @GET("book")
    fun getBooksList(@Query("editorial") editorial: String): Call<List<Book>>

    //parameter was corrected
    @POST("book")
    fun saveBook(@Body newBook: Book): Call<Book>

    @FormUrlEncoded
    @PUT("book/{isbn}")
    fun updateBook(@Path("isbn") isbn:Int,
                   @Field("name") name: String,
                   @Field("description") description: String,
                   @Field("editorial") editorial: String

    ): Call<Book>

    //added parameter
 @DELETE("book/{isbn}")
 fun deleteBook(@Path("isbn") isbn: Int): Call<Unit>

}
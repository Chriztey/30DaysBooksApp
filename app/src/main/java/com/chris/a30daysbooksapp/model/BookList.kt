package com.chris.a30daysbooksapp.model

import com.chris.a30daysbooksapp.R

object BookList {
    val BookListing = listOf<Books>(
        Books(
            name = R.string.book1name,
            synopsis = R.string.book1sy,
            rating = 4,
            image = R.drawable.ikigai
        ),
        Books(
            name = R.string.book2name,
            synopsis = R.string.book2sy,
            rating = 3,
            image = R.drawable.atomic
        ),


    )
}
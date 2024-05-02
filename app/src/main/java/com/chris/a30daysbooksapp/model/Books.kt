package com.chris.a30daysbooksapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Books(
    @StringRes val name: Int,
    @StringRes val synopsis: Int,
    @DrawableRes val image: Int,
    val rating: Int
)

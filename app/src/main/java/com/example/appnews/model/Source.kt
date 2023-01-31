package com.example.appnews.model


import java.io.Serializable

data class Source (

    val id : String?,
    val name : String
) : Serializable {
    constructor(): this(
        id = null,
        name = ""
    )
}
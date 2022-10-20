package com.example.mmvmwithretrofit2.retrofit.entity

import com.google.gson.annotations.SerializedName

class PersonEntity {
    /*
     * That's the class the represents the business and is parsed by gson
     * */

    @SerializedName("name")
    var name: String = ""

    @SerializedName("skinColor")
    var skinColor: String = ""


    /*
     * The annotation @SerializedName receives an argument which says what's the correspondent of the attribute in the data that comes from API.
     * */
}
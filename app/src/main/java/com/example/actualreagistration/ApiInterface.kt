package com.example.actualreagistration


import com.example.actualreagistration.dataClasses.registerModelResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("students/register")
    fun registerStudents(@Field("name") name:String,
                         @Field("phone_number") phone_number:String,
                         @Field("date_of_birth") date_of_birth:String,
                         @Field("nationality") nationality:String,
                         @Field("password") password:String,
                         @Field("email") email:String

    ): Call<registerModelResponse>


}
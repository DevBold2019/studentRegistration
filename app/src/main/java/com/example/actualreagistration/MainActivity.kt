package com.example.actualreagistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.actualreagistration.dataClasses.registerModelResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var etName:EditText
    lateinit var etDob:EditText
    lateinit var spnNationality:Spinner
    lateinit var etPhoneNumber:EditText
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText
    lateinit var btnNext:Button
    lateinit var retrofit: Retrofit
    lateinit var dialogLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        CastViews()
        clickRegister()

        retrofit=Retrofit.Builder()
            .baseUrl("http://13.244.243.129")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun CastViews(){

        etName=findViewById(R.id.etName)
        etDob=findViewById(R.id.etDob)
        etPhoneNumber=findViewById(R.id.etPhoneNumber)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        spnNationality=findViewById(R.id.spnNationality)
        btnNext=findViewById(R.id.btnNext)

        dialogLayout=findViewById(R.id.progressLayout)

        var nationality = arrayOf("Kenya","Uganda","Rwanda","Tanzania","Ethopia","Sudan")
        var nationalityAdapter=ArrayAdapter(baseContext,android.R.layout.simple_spinner_dropdown_item,nationality)
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnNationality.adapter=nationalityAdapter


    }

    fun getCountries(){

    }

    fun getCourses(){


    }

    fun clickRegister() {

        btnNext.setOnClickListener {

            var name = etName.text.toString()
            var dob = etDob.text.toString()
            var phonenumber = etPhoneNumber.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            var nationality = spnNationality.selectedItem.toString()

           /* var intent = Intent(baseContext,logInActivity::class.java)
            startActivity(intent)*/

            if (name.trim() == "" || dob.trim() == "" || phonenumber.trim() == "" ||
                email.trim() == "" || password.trim() == "" || nationality.trim() == ""
            ){

                Toast.makeText(baseContext,"Empty params\nAll fields required",Toast.LENGTH_SHORT).show()
                Log.d("","EMPTY PARAMS")

                return@setOnClickListener

            }

            dialogLayout.visibility= View.VISIBLE
            var intent = Intent(baseContext,logInActivity::class.java)
            startActivity(intent)
            //registerNewStudent(name,phonenumber,dob,nationality,password,email)

        }
    }

    fun registerNewStudent(
        name: String,
        phonenumber: String,
        dob: String,
        nationality: String,
        password: String,
        email: String
    ) {

        val apiInterface: ApiInterface =retrofit.create(ApiInterface::class.java)
        val call: Call<registerModelResponse> = apiInterface.registerStudents(name,phonenumber,dob,nationality
            ,password,email)

        call.enqueue(object : Callback<registerModelResponse> {

            override fun onResponse(call: Call<registerModelResponse>, response: Response<registerModelResponse>) {

                if (response.isSuccessful){

                    val nameRegistered= response.body()!!.name

                    Toast.makeText(baseContext,"Registered:\t$nameRegistered",Toast.LENGTH_SHORT).show()
                    var intent = Intent(baseContext, logInActivity::class.java)
                    startActivity(intent)

                }
                Log.d("MESSAGE","${response.message()}")

            }

            override fun onFailure(call: Call<registerModelResponse>, t: Throwable) {


                Toast.makeText(baseContext,"Error registering",Toast.LENGTH_SHORT).show()
                Log.d("FAILED","${t.localizedMessage.toString()}")
            }

        })

    }

    }


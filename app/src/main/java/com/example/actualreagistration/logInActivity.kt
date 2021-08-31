package com.example.actualreagistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class logInActivity : AppCompatActivity() {

    lateinit var nameEditText:TextInputLayout
    lateinit var passwordEditText:TextInputLayout


    lateinit var name:String
    lateinit var password:String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)



        nameEditText=findViewById(R.id.tilName)
        passwordEditText=findViewById(R.id.tilPassword)

        name=nameEditText.editText.toString()
        password=passwordEditText.editText.toString()

    }

    fun Login(view: View) {

        if (name.trim().equals("") ||password.trim().equals("") ){

            Toast.makeText(baseContext,"Empty params\nAll fields required",Toast.LENGTH_SHORT).show();

            Log.d("hey message","Empty params");
            return
        }

        Toast.makeText(baseContext,"clicked",Toast.LENGTH_SHORT).show();


    }
}
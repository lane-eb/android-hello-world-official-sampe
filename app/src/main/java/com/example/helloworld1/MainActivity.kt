package com.example.helloworld1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
//import android.view.View
import android.widget.RadioGroup
import okhttp3.*
import java.io.IOException

const val EXTRA_MESSAGE = "com.example.helloworld1.MESSAGE"

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        run("https://api.github.com/users/Evin1-/repos")
    }

    private fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body?.string())
        })
    }

    // TODO: Record red package feature.
    fun nextQuestions(view: View) {
        val radioGroupQuestion1 = findViewById<RadioGroup>(R.id.RadioGroupQuestion1)

        val checkedRadioButtonQuestion1 = findViewById<RadioButton>(radioGroupQuestion1.checkedRadioButtonId)
        println(checkedRadioButtonQuestion1.text.toString())

        if (checkedRadioButtonQuestion1.text == getString(R.string.question_1_yes)) {
            println("====yes =====")
        }

//        val message = radioGroupAnswer1.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, checkedRadioButtonQuestion1.text.toString())
        }
        startActivity(intent)
    }

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }



//    fun onRadioButtonClicked(view: View) {
//        if (view is RadioButton) {
//            // Is the button now checked?
//            val checked = view.isChecked
//
//            // Check which radio button was clicked
//            when (view.getId()) {
//                R.id.radio_pirates ->
//                    if (checked) {
//                        // Pirates are the best
//                    }
//                R.id.radio_ninjas ->
//                    if (checked) {
//                        // Ninjas rule
//                    }
//            }
//        }
//    }
}
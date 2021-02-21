package com.example.helloworld1

//import android.view.View
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import okhttp3.*
import java.io.IOException


const val EXTRA_MESSAGE = "com.example.helloworld1.MESSAGE"

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.Lane)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView1 = questionTextView(R.string.question_1_title, 0)
        val radioGroup1 = questionRadioGroup(textView1.id)
        val textView2 = questionTextView(R.string.question_2_title, radioGroup1.id)
        val radioGroup2 = questionRadioGroup(textView2.id)
        buttonNext(radioGroup2.id)
    }

    private fun questionTextView(title: Int, alignId: Int): TextView {
        val layout = findViewById<View>(R.id.main_layout) as ConstraintLayout

        val textView = TextView(this, null, 0, R.style.GreenText)
//        val textView = TextView(this)
        textView.id = TextView.generateViewId()
        textView.text = getString(title)
        textView.textSize = 24f

        val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(32, 16, 0, 0);
        textView.layoutParams = layoutParams;
        layout.addView(textView, layoutParams)

        val set = ConstraintSet()
        set.clone(layout)
        set.connect(textView.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 16)
        if (alignId != 0) {
            set.connect(textView.id, ConstraintSet.TOP, alignId, ConstraintSet.BOTTOM, 16)
        }
        set.applyTo(layout)

        return textView
    }

    private fun questionRadioGroup(alignId: Int): RadioGroup {
        val layout = findViewById<View>(R.id.main_layout) as ConstraintLayout

        val radioGroup = RadioGroup(this)
        radioGroup.id = TextView.generateViewId()
        radioGroup.orientation = LinearLayout.HORIZONTAL

        val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(36, 8, 0, 0)
        radioGroup.layoutParams = layoutParams
        layout.addView(radioGroup, layoutParams)

        val set = ConstraintSet()
        set.clone(layout)
        set.connect(radioGroup.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 16)
        set.connect(radioGroup.id, ConstraintSet.TOP, alignId, ConstraintSet.BOTTOM, 16)
        set.applyTo(layout)

        addRadioButton(radioGroup, R.string.question_1_yes)
        addRadioButton(radioGroup, R.string.question_1_no)
        return radioGroup
    }

    private fun addRadioButton(radioGroup: RadioGroup, text: Int) {
        val radioButton = RadioButton(this)
        radioButton.id = RadioButton.generateViewId()
        radioButton.text = getString(text)
        radioButton.textSize = 18f

        radioButton.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        radioGroup.addView(radioButton)
    }

    private fun buttonNext(alignId: Int) {
        val layout = findViewById<View>(R.id.main_layout) as ConstraintLayout

        val myButton = Button(this, null, 0, R.style.GreenText)
//        val myButton = Button(this)
        myButton.id = Button.generateViewId()
        myButton.text = getString(R.string.Question_Button_next_text)
//        myButton.setBackgroundResource(android.R.drawable.btn_default);

        val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(32, 16, 0, 0);
        myButton.layoutParams = layoutParams;
        layout.addView(myButton, layoutParams)

        val set = ConstraintSet()
        set.clone(layout)
        set.connect(myButton.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 16);
        set.connect(myButton.id, ConstraintSet.TOP, alignId, ConstraintSet.BOTTOM, 16);
        set.applyTo(layout)
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
}
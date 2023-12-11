package com.example.kotlinday2sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlinday2sharedpreferences.ui.theme.KotlinDay2SharedPreferencesTheme

class MainActivity : ComponentActivity() {
    private lateinit var nameText:EditText
    private lateinit var ageText:EditText

    private lateinit var sf:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.editTextText2)
        ageText = findViewById(R.id.editTextNumber)

        // mode private means only our app can see it
        sf = getSharedPreferences("save_file", MODE_PRIVATE)
        editor = sf.edit()

    }

    override fun onPause() {
        super.onPause()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()

        editor.apply {
            // think of it as storing key,val pairs like in a map
            putString("sfName", name)
            putInt("sfAge", age)
            commit() // essential step to make sure data gets saved
        }
    }

    override fun onResume() {
        super.onResume()
        val getName = sf.getString("sfName", null)
        val getAge = sf.getInt("sfAge", 0)
        nameText.setText(getName)

        if(getAge != 0) {
            ageText.setText(getAge.toString())
        }
    }
}
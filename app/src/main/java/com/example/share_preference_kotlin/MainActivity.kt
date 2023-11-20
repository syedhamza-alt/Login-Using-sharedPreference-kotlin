package com.example.share_preference_kotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.share_preference_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedpreferences: SharedPreferences
    private var email_sh: String? = null
    private var password_sh: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        email_sh = sharedpreferences.getString(EMAIL_KEY, null)
        password_sh = sharedpreferences.getString(PASSWORD_KEY, null)


            binding.loginBtn.setOnClickListener {
                val email = binding.emailEt.text.toString();
                val password = binding.passwordEt.text.toString();
                if (TextUtils.isEmpty(binding.emailEt.text.toString()) && TextUtils.isEmpty(binding.passwordEt.text.toString())) {
                    // this method will call when email and password fields are empty.
                    Toast.makeText(
                        this@MainActivity,
                        "Please Enter Email and Password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val editor = sharedpreferences.edit()

                    // below two lines will put values for
                    // email and password in shared preferences.
                    editor.putString(EMAIL_KEY, binding.emailEt.text.toString())
                    editor.putString(PASSWORD_KEY, binding.passwordEt.text.toString())

                    // to save our data with key and value.
                    editor.apply()

                    // starting new activity.
                    val i = Intent(this@MainActivity, showDataActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }

        }
    override fun onStart() {
        super.onStart()
        if (email_sh != null && password_sh != null) {
            val i = Intent(this@MainActivity, showDataActivity::class.java)
            startActivity(i)
        }
    }

}


package com.example.share_preference_kotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.share_preference_kotlin.databinding.ActivityMainBinding
import com.example.share_preference_kotlin.databinding.ActivityShowDataBinding

class showDataActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
    }
    private lateinit var binding: ActivityShowDataBinding
    private lateinit var sharedpreferences: SharedPreferences
    private var email: String? = null
    private var password: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        email = sharedpreferences.getString(EMAIL_KEY, null)
        password = sharedpreferences.getString(PASSWORD_KEY, null)

        val welcomeTv=binding.dataTv;
        welcomeTv.text="Welcome : $email , Your password is : $password"

        binding.signoutBtn.setOnClickListener {

            val editor = sharedpreferences.edit()

            editor.clear()

            editor.apply()

            val i = Intent(this@showDataActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }

    }
}
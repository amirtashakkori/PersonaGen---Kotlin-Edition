package com.example.personagen.GenerateUser

import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.personagen.Data.Model.User
import com.example.personagen.R
import com.example.personagen.UserInfo.UserInfoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class GenerateUserActivity : AppCompatActivity() {

    private val viewModel: GenerateViewModel by viewModel()

    private lateinit var genderSpinner: AutoCompleteTextView
    private lateinit var natSpinner : AutoCompleteTextView
    private lateinit var generateBtn : AppCompatButton
    private lateinit var backBtn : ImageView

    fun cast(){
        genderSpinner = findViewById(R.id.genderSpinner)
        natSpinner = findViewById(R.id.natSpinner)
        generateBtn = findViewById(R.id.generateBtn)
        backBtn = findViewById(R.id.backBtn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_generate_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cast()

        setGenderSpinner()
        setNatSpinner()

        generateBtn.setOnClickListener{
            generateBtnClicked()
        }

        backBtn.setOnClickListener{
            finish()
        }


    }

    private fun setGenderSpinner(){
        val genderSpinnerItems  = resources.getStringArray(R.array.gender)
        val genderSpinnerAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.spinnerTv, genderSpinnerItems )
        genderSpinner.setAdapter(genderSpinnerAdapter)
        genderSpinner.setText(genderSpinnerItems [0], false)

    }

    private fun setNatSpinner(){
        val natSpinnerItems = resources.getStringArray(R.array.nationalities)
        val natSpinnerAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.spinnerTv, natSpinnerItems )
        natSpinner.setAdapter(natSpinnerAdapter)
        natSpinner.setText(natSpinnerItems[0], false)
    }

    private fun getNat(nat: String): String {
        return when (nat) {
            "United State" -> "us"
            "United Kingdom" -> "gb"
            "Australia" -> "au"
            "Canada" -> "ca"
            "Switzerland" -> "ch"
            "Germany" -> "de"
            "Spain" -> "es"
            "France" -> "fr"
            "Turkey" -> "tr"
            "Ukraine" -> "ua"
            else -> "nl"
        }
    }

    private fun generateBtnClicked(){
        val gender: String = genderSpinner.text.toString().lowercase()
        val nat : String = getNat(natSpinner.text.toString())

        viewModel.generateUser(gender, nat)
        viewModel.user.observe(this, Observer {
            if (it != null){
                val intent= Intent(this, UserInfoActivity::class.java).apply {
                    putExtra("user", it)
                    putExtra("deleteBtnVis", false)
                }
                startActivity(intent)
                finish()
            } else{
                viewModel.error.observe(this, Observer {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                })
            }
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            Log.i("apiError", it)
        })
    }


}
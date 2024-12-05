package com.example.personagen.UserInfo

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.personagen.Common.getParcelableCompat
import com.example.personagen.Data.Model.User
import com.example.personagen.R
import com.example.personagen.UserInfo.Fragments.AccountInfo
import com.example.personagen.UserInfo.Fragments.Communication
import com.example.personagen.UserInfo.Fragments.Location
import com.example.personagen.UserInfo.Fragments.PersonalInfo
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModel()

    lateinit var backBtn: CardView
    lateinit var deleteBtn: CardView
    lateinit var userImg: ImageView
    lateinit var userNameTv: TextView
    lateinit var infoTb: TabLayout
    lateinit var container: FrameLayout

    private var user: User? = null

    private fun cast(){
        backBtn = findViewById(R.id.backBtn)
        deleteBtn = findViewById(R.id.deleteBtn)
        userImg = findViewById(R.id.userImg)
        userNameTv = findViewById(R.id.userNameTv)
        infoTb = findViewById(R.id.infoTb)
        container = findViewById(R.id.container)
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cast()

        val deleteBtnVisibility = intent.getBooleanExtra("deleteBtnVis", true)
        if (deleteBtnVisibility)
            setDeleteBtnVisibility(true)
        else
            setDeleteBtnVisibility(false)

        user = intent.getParcelableCompat("user")
        user?.let {
            val fullName = "${it.name.first} ${it.name.last}"
            Picasso.get().load(it.picture.large).into(userImg)
            userNameTv.text = fullName
            loadFragment(PersonalInfo())

            setTabs()

            backBtn.setOnClickListener{
                finish()
            }

            deleteBtn.setOnClickListener{
                viewModel.deleteUser(user!!)
                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }


    private fun setTabs() {
        val tab1: TabLayout.Tab = infoTb.newTab().setIcon(R.drawable.ic_ui_user)
        val tab2: TabLayout.Tab = infoTb.newTab().setIcon(R.drawable.ic_ui_call)
        val tab3: TabLayout.Tab = infoTb.newTab().setIcon(R.drawable.ic_ui_email)
        val tab4: TabLayout.Tab = infoTb.newTab().setIcon(R.drawable.ic_ui_location)

        infoTb.apply {
            addTab(tab1)
            addTab(tab2)
            addTab(tab3)
            addTab(tab4)
            selectTab(tab1)
        }

        val colorSecondary = TypedValue().apply {
            theme.resolveAttribute(android.R.attr.colorAccent, this, true)
        }.data

        val colorBackground = TypedValue().apply {
            theme.resolveAttribute(android.R.attr.colorBackground, this, true)
        }.data

        tab1.icon?.setTint(colorSecondary)

        infoTb.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.icon?.setTint(colorSecondary)
                val fragment = when (tab.position) {
                    0 -> PersonalInfo()
                    1 -> Communication()
                    2 -> AccountInfo()
                    3 -> Location()
                    else -> null
                }
                fragment?.let { loadFragment(fragment) }
            }

            @SuppressLint("ResourceAsColor")
            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.icon?.setTint(colorBackground)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun loadFragment(fragment: Fragment){
        fragment.let {
            val bundle = Bundle().apply {
                putParcelable("user", this@UserInfoActivity.user)
            }
            it.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }

    fun setDeleteBtnVisibility(show: Boolean){
        deleteBtn.visibility = if (show) View.VISIBLE else View.GONE
    }


}
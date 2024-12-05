package com.example.personagen.Main

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.personagen.Common.isNetworkAvailable
import com.example.personagen.Common.personaActivity
import com.example.personagen.Data.Model.User
import com.example.personagen.GenerateUser.GenerateUserActivity
import com.example.personagen.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : personaActivity(), UserAdapter.ChangeListener {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var userAdapter: UserAdapter

    private lateinit var userRv: RecyclerView
    private lateinit var addBtn: FloatingActionButton
    private lateinit var emptyStateImg: ImageView

    fun cast(){
        userRv = findViewById(R.id.userRv)
        addBtn = findViewById(R.id.addBtn)
        emptyStateImg = findViewById(R.id.emptyStateImg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cast()

        viewModel.userList.observe(this, Observer {
            if (it.isNullOrEmpty()){
                setEmptyStateVisibility(true)
            } else{
                setEmptyStateVisibility(false)
                userRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                userAdapter = UserAdapter(this, it, this)
                userRv.adapter = userAdapter
            }
        })

        addBtn.setOnClickListener{
            if (!isNetworkAvailable()){
                showNoInternetDialog(this, getString(R.string.no_internet_user_generating))
            }else{
                val intent = Intent(this, GenerateUserActivity::class.java)
                startActivity(intent)
            }
        }


    }

    companion object {
        fun showNoInternetDialog(context: Context, message: String) {
            AlertDialog.Builder(context)
                .setTitle("No Internet Connection")
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)
                .show()
        }
    }

    fun setEmptyStateVisibility(show: Boolean){
        emptyStateImg.visibility = if (show) View.VISIBLE else View.GONE
        userRv.visibility = if (!show) View.VISIBLE else View.GONE
    }

    override fun onDelete(user: User) {
        viewModel.deleteUser(user)
    }

}
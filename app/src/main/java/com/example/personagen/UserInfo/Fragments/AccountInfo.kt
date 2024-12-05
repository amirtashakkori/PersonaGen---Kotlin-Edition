package com.example.personagen.UserInfo.Fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.personagen.Common.getParcelableCompat
import com.example.personagen.Data.Model.User
import com.example.personagen.R


class AccountInfo : Fragment() {

    lateinit var userNameTv: TextView
    lateinit var passwordTv: TextView
    lateinit var dorTv: TextView

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        user = bundle?.getParcelableCompat("user")

        userNameTv = view.findViewById(R.id.userNameTv)
        passwordTv = view.findViewById(R.id.passwordTv)
        dorTv = view.findViewById(R.id.dorTv)

        user?.let {
            userNameTv.text = it.login.username
            setUpTextViewCopy(userNameTv)

            passwordTv.text = it.login.password
            setUpTextViewCopy(passwordTv)

            dorTv.text = it.dob.date.substring(0,9)
            setUpTextViewCopy(dorTv)
        }

    }

    private fun setUpTextViewCopy(textView: TextView) {
        textView.setOnClickListener {
            val text = textView.text.toString()
            val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("copied text", text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Copied!", Toast.LENGTH_SHORT).show()
        }
    }

}
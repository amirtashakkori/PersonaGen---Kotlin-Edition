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
import java.util.Locale


class PersonalInfo : Fragment() {

    lateinit var firstNameTv: TextView
    lateinit var lastNameTv: TextView
    lateinit var genderTv: TextView
    lateinit var ageTv: TextView
    lateinit var dobTv: TextView

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        user = bundle?.getParcelableCompat("user")

        firstNameTv = view.findViewById(R.id.firstNameTv)
        lastNameTv = view.findViewById(R.id.lastNameTv)
        genderTv = view.findViewById(R.id.genderTv)
        ageTv = view.findViewById(R.id.ageTv)
        dobTv = view.findViewById(R.id.dobTv)

        user?.let {
            firstNameTv.text = it.name.first
            setUpTextViewCopy(firstNameTv)

            lastNameTv.text = it.name.last
            setUpTextViewCopy(lastNameTv)

            genderTv.text = it.gender
            setUpTextViewCopy(genderTv)

            val age = it.dob.age.toString()
            ageTv.text = age
            setUpTextViewCopy(ageTv)

            dobTv.text = it.dob.date.substring(0,9)
            setUpTextViewCopy(dobTv)
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










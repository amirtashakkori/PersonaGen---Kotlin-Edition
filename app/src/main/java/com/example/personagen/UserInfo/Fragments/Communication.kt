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

class Communication : Fragment() {

    lateinit var phoneTv: TextView
    lateinit var cellphoneTv: TextView
    lateinit var emailTv: TextView

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_communication, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        user = bundle?.getParcelableCompat("user")

        phoneTv = view.findViewById(R.id.phoneTv)
        cellphoneTv = view.findViewById(R.id.cellphoneTv)
        emailTv = view.findViewById(R.id.emailTv)

        user?.let {
            phoneTv.text = it.phone
            setUpTextViewCopy(phoneTv)

            cellphoneTv.text = it.cell
            setUpTextViewCopy(cellphoneTv)

            emailTv.text = it.email
            setUpTextViewCopy(emailTv)
        }

    }

    private fun setUpTextViewCopy(textView: TextView) {
        textView.setOnClickListener {
            val text = textView.text.toString()
            val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("copied text", text)
            clipboard.setPrimaryClip(clip)
        }
    }


}
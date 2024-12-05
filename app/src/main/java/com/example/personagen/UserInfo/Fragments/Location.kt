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


class Location : Fragment() {

    lateinit var countryTv: TextView
    lateinit var stateTv: TextView
    lateinit var cityTv: TextView
    lateinit var natTv: TextView
    lateinit var addressTv: TextView
    lateinit var postCodeTv: TextView

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        user = bundle?.getParcelableCompat("user")

        countryTv = view.findViewById(R.id.countryTv)
        stateTv = view.findViewById(R.id.stateTv)
        cityTv = view.findViewById(R.id.cityTv)
        natTv = view.findViewById(R.id.natTv)
        addressTv = view.findViewById(R.id.addressTv)
        postCodeTv = view.findViewById(R.id.postCodeTv)

        user?.let {
            countryTv.text = it.location.country
            setUpTextViewCopy(countryTv)

            stateTv.text = it.location.state
            setUpTextViewCopy(stateTv)

            cityTv.text = it.location.city
            setUpTextViewCopy(cityTv)

            natTv.text = it.nat
            setUpTextViewCopy(natTv)

            val address = "${it.location.street.number} ${it.location.street.number}"
            addressTv.text = address
            setUpTextViewCopy(addressTv)

            postCodeTv.text = it.location.postcode
            setUpTextViewCopy(postCodeTv)
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









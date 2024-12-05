package com.example.personagen.Common

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

interface personaView{

}

abstract class pesonaFragment: Fragment(), personaView {

}

abstract class personaActivity: AppCompatActivity(), personaView {

}

abstract class personaViewModel

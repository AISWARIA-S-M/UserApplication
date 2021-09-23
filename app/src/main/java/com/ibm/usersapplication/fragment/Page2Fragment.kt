package com.ibm.usersapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.ibm.usersapplication.R
import com.ibm.usersapplication.model.UserDetail

class Page2Fragment : Fragment() {

    //private var bind : FragmentPage2Binding? = null
    //private val binding get() = bind!!
    private val userdetail: Page2FragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //inflater.inflate(R.id.fragment_userdetail,view)
        //bind = FragmentPage2Binding.inflate(inflater, container, false)
        //val view = binding.root



      //  binding.name.text =
        return inflater.inflate(R.layout.fragment_page2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val textname : TextView = view.findViewById(R.id.name)
        val textemail : TextView = view.findViewById(R.id.emailId)
        val textid : TextView = view.findViewById(R.id.id)
        val textmobile : TextView = view.findViewById(R.id.mobile)
        val textgender : TextView = view.findViewById(R.id.gender)
        val userdetails: UserDetail = userdetail.paramterName
        //val bundle : Bundle = getArguments()



        textname.text = userdetails.name
        textemail.text = userdetails.email
        textid.text = userdetails.id
        textmobile.text = userdetails.contact.mobile
        textgender.text = userdetails.gender
     //   Log.d("name", userdetails.toString())
    }
}
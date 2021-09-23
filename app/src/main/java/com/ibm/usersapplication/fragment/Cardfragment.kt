package com.ibm.usersapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibm.usersapplication.R
import com.ibm.usersapplication.adapter.UserAdapter
import com.ibm.usersapplication.model.UserDetail
import com.ibm.usersapplication.viewmodel.UserViewModel

class Cardfragment : Fragment() {

    private var layoutManager : RecyclerView.LayoutManager? = null
    private lateinit var userAdapter: UserAdapter
    private lateinit var viewModel: UserViewModel
    private lateinit var recyclerView : RecyclerView
    private var arrayList = ArrayList<UserDetail>()

//    private var bind : FragmentCardfragmentBinding? =null
//    private val binding get()= bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        bind = FragmentCardfragmentBinding.inflate(inflater, container, false)
//        val view = binding.root
//       return view
        return inflater.inflate(R.layout.userdetaillist, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById(R.id.recyclerview)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        initializerAdapter()
    }

    private fun initializerAdapter(){
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager= layoutManager
        userAdapter = context?.let { UserAdapter(arrayList,listener) }!!
        recyclerView.adapter = userAdapter
        observeData()
    }
    private val listener = UserAdapter.OnClickListener { user ->
        // Add action to navigate
      findNavController().
      navigate(
          CardfragmentDirections.cardtopage2(user)
      )
    }

    private fun observeData() {
        viewModel.data.observe(viewLifecycleOwner, {
            if(it != null && it.users.isNotEmpty()){
                Log.i("data", it.toString())
                userAdapter.setUserList(it.users)
            }
            else{
                Log.i("data", "Sorry!!!")
            }
        })
        viewModel.fetchuserdata()
    }
}
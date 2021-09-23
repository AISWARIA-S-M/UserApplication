package com.ibm.usersapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibm.usersapplication.R
import com.ibm.usersapplication.model.UserDetail

class UserAdapter(
    private var arrayList: ArrayList<UserDetail>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_cardfragment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = arrayList[position]

        holder.name.text = currentItem.name
        holder.emailId.text = currentItem.email
     holder.itemView.setOnClickListener {
            onClickListener.onClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){//,MyAdapterListener{
        var name: TextView = itemView.findViewById(R.id.name)
        var emailId: TextView = itemView.findViewById(R.id.email_id)

    }
    class OnClickListener(val clickListener: (user: UserDetail) -> Unit) {
        fun onClick(user: UserDetail) = clickListener(user)
    }
    //update
    fun setUserList(userList: ArrayList<UserDetail>) {
        arrayList = userList
        notifyDataSetChanged()
    }

}
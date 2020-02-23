package com.example.myapplication

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(items : List<Contacts> , context : Context) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    private var contacts : List<Contacts> = items
    private var context : Context = context
    private var mListener : AdapterView.OnItemClickListener? =null


    class ContactsViewHolder(item : View) : RecyclerView.ViewHolder(item) , View.OnCreateContextMenuListener
    {
        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu!!.add(this.adapterPosition,121,0,"Edit")
            menu.add(this.adapterPosition,122,1,"Delete")
        }

        var textViewName: TextView = item.findViewById(R.id.contacts_text)
        var profile: ImageView = item.findViewById(R.id.image_contact)
        var linearLayout: LinearLayout = item.findViewById(R.id.linearlayout)
        init {

            linearLayout.setOnCreateContextMenuListener(this)
        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val v : View = LayoutInflater.from(context).inflate(R.layout.list_card,parent,false)
        return ContactsViewHolder(v)
    }

    override fun getItemCount() = contacts.size



    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        var currentUser = contacts[position]

        //   val user : User = users[position]

        holder.textViewName.text =  currentUser.firstName
        if(currentUser.image!=null)
        {
            holder.profile.setImageBitmap(currentUser.image)
        }
        else
        {
            holder.profile.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.ic_launcher_round))
        }
        //listUsers[position].username

        holder.linearLayout.setOnClickListener()
        {
            Toast.makeText(context,"You clicked $position",Toast.LENGTH_SHORT).show()
        }




    }
    fun setContacts(users : List<Contacts>)
    {
        this.contacts=users
        notifyDataSetChanged()
    }

}

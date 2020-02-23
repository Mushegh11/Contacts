package com.example.myapplication

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var contactsList : MutableList<Contacts> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listcontacts.layoutManager = LinearLayoutManager(this)
        fetchContacts()



    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }





    fun fetchContacts()
    {

        val uri : Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection : Array<String>? = null
        val selection : String?= null
        val selectionArgs :  Array<String>? = null
        val sortOrder : String? = null
        var resolver : ContentResolver = contentResolver
       var cursor : Cursor? = resolver.query(uri,projection, selection, selectionArgs, sortOrder)
        while(cursor!!.moveToNext())
        {
            var name:String = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            var number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            var obj : Contacts = Contacts()
            obj.firstName = name
            obj.number = number

            val photouri =  cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
            if(photouri != null)
            {
                obj.image= MediaStore.Images.Media.getBitmap(contentResolver,Uri.parse(photouri))
            }


            contactsList.add(obj)



        }
        listcontacts.adapter = ContactsAdapter(contactsList,this)
        cursor.close()
    }
}

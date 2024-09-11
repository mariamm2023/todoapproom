package com.example.todoapproom.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapproom.R

import com.example.todoapproom.database.model.todo
import com.example.todoapproom.database.tododatabase
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class updatetodoactivity:AppCompatActivity() {
    lateinit var date: TextView
    lateinit var updatebutton: Button
    lateinit var title: EditText
    lateinit var desc: EditText
    lateinit var updatetitle_layout: TextInputLayout
    lateinit var updatedetails_layout: TextInputLayout
    lateinit var todo: todo
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.updatetodoactivity)
        inital()
        recievedata()

    }

    private fun updatetodo(todo: todo) {
        tododatabase.getinstance(this).tododao().updatetodo(todo)
        Toast.makeText(this, "update sucessful", Toast.LENGTH_SHORT).show()
        onBackPressed()

    }


    private fun inital() {
        updatebutton=findViewById(R.id.savechange)
        updatetitle_layout=findViewById(R.id.update_layout)
        desc=findViewById(R.id.updatetododetails)
        title=findViewById(R.id.updatetodo)
        updatedetails_layout=findViewById(R.id.updatedescription_layout)
        updatetitle_layout=findViewById(R.id.update_layout)
        date=findViewById(R.id.dateupdatetv)
        date.setOnClickListener(View.OnClickListener {
            showdatepicker()
        })
        updatebutton.setOnClickListener(View.OnClickListener {
            var isvalid:Boolean=validate()
            if(isvalid){
                var title:String=title.text.toString()
                var desc:String=desc.text.toString()
                var date=Calendar.getInstance().clear()
               var updatetodo=todo(todo.id,title,desc,date=Date(calendar.timeInMillis),false)
                updatetodo(updatetodo)
            }


        })
    }


    private fun recievedata() {
       todo = (intent.getSerializableExtra("todo") as?todo)!!
        title.setText(todo.name)
        desc.setText(todo.description)
        date.setText(todo.date.toString())


    }
    fun validate(): Boolean {
        var isvalid=true
        if (updatedetails_layout.editText?.text.toString().isBlank()) {
            updatetitle_layout.setError("please Enter Title")
            isvalid=false
        } else {
            updatetitle_layout.setError(null)
        }
        if (  updatedetails_layout.editText?.text.toString().isBlank()) {
            updatedetails_layout.setError("please Enter description")
            isvalid=false

        } else {
            updatedetails_layout.setError(null)
        }
        return isvalid

    }
    private val calendar = Calendar.getInstance() // تهيئة الـ Calendar

    private fun showdatepicker() {
        val datapicker = DatePickerDialog(this,
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(
                    picker: DatePicker?,
                    year: Int,
                    month: Int,
                    dayofmonth: Int
                ) {
                    calendar.set(Calendar.DAY_OF_MONTH, dayofmonth)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.YEAR, year)
                    showdate()
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datapicker.show()
    }
    private fun showdate() {
        date.setText(simpledateformat.format(Date(calendar.timeInMillis)))

    }
    val simpledateformat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())


}

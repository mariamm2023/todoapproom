package com.example.todoapproom.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.todoapproom.R
import com.example.todoapproom.database.model.todo
import com.example.todoapproom.database.tododatabase

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class addtodobuttomsheet:BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }
    var onaddfinishlistene:onaddfinishlistener?=null
    interface onaddfinishlistener{
        fun onfinish()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initview()

    }

    lateinit var datetv: TextView
    lateinit var finishbutton: Button
    lateinit var titleview: EditText
    lateinit var decstv: EditText
    lateinit var title_layout: TextInputLayout
    lateinit var desc_layout: TextInputLayout


    private fun initview() {
        datetv = requireView().findViewById(R.id.datetv)
        finishbutton = requireView().findViewById(R.id.finish)
        titleview = requireView().findViewById(R.id.titleedittext)
        decstv = requireView().findViewById(R.id.descriotionedittext)
        title_layout = requireView().findViewById(R.id.title_layout)
        desc_layout = requireView().findViewById(R.id.description_layout)
        datetv.setOnClickListener {
            showdatepicker()
            showdate()
        }
        finishbutton.setOnClickListener {
            var title = titleview.text.toString()
            val desc = decstv.text.toString()
           if(validate(title,desc)) {
               calender.clear(Calendar.HOUR)
               calender.clear(Calendar.MINUTE)
               calender.clear(Calendar.SECOND)
               calender.clear(Calendar.MILLISECOND)
               var todo=todo(name = title, description = desc, date =Date(calender.timeInMillis), isDone = false ) //لاستدعاء الداتا بيز
               tododatabase.getinstance(requireContext().applicationContext).tododao().addtode(todo)
               Toast.makeText(requireContext(), "to do added sussesfully", Toast.LENGTH_SHORT).show()
               dismiss()
               onaddfinishlistene?.onfinish()
           }


        }


    }

    val calender = Calendar.getInstance()
    val simpledateformat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())


    private fun showdate() {
        datetv.setText(simpledateformat.format(Date(calender.timeInMillis)))

    }

    private fun showdatepicker() {
        val datapicker = DatePickerDialog(requireContext(),
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(
                    picker: DatePicker?,
                    year: Int,
                    month: Int,
                    dayofmonth: Int
                ) {
                    calender.set(Calendar.DAY_OF_MONTH, dayofmonth)
                    calender.set(Calendar.MONTH, month)
                    calender.set(Calendar.YEAR, year)
                    showdate()


                }

            },
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )
        datapicker.show()
    }

     fun validate(title: String, desc: String): Boolean {
        var isvalid=true
        if (title.isBlank()) {
            title_layout.setError("please Enter Title")
            isvalid=false
        } else {
            title_layout.setError(null)
        }
        if (desc.isBlank()) {
            desc_layout.setError("please Enter description")
            isvalid=false

        } else {
            desc_layout.setError(null)
        }
        return isvalid

    }
}

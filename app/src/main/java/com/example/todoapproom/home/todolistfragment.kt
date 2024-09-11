package com.example.todoapproom.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapproom.R
import com.example.todoapproom.database.model.todo
import com.example.todoapproom.database.tododatabase
import com.example.todoapproom.swaptodelete
import java.util.Calendar
import java.util.Date

class todolistfragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var recyclerView: RecyclerView
    private var todoAdapter = todoAdapter()
    private var date: Date = Date()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // تهيئة CalendarView
        calendarView = view.findViewById(R.id.calender)

        // تهيئة RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview)
        initRecyclerView()

        // تعيين المستمع لتغييرات التاريخ في CalendarView
        calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
                reloadToDoList(dayOfMonth, month, year)
            }
        })
        val swaptodeletcallback=object :swaptodelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val positipon=viewHolder.adapterPosition
                data.removeAt(positipon)
                recyclerView.adapter?.notifyItemRemoved(positipon)
                Toast.makeText(requireContext(), "task removed", Toast.LENGTH_SHORT).show()


            }

        }
        val itemtochhelper=ItemTouchHelper(swaptodeletcallback)
        itemtochhelper.attachToRecyclerView(recyclerView)
    }

    private fun initRecyclerView() {
        recyclerView.adapter = todoAdapter
        onitemclicktobeupdated()
    }

    private fun reloadToDoList(dayOfMonth: Int, month: Int, year: Int) {
        // إنشاء كائن Calendar وتعيين التاريخ
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        calendar.clear(Calendar.HOUR)
        calendar.clear(Calendar.MINUTE)
        calendar.clear(Calendar.SECOND)
        calendar.clear(Calendar.MILLISECOND)


        date = calendar.time
        getToDoFromDatabase(date)
    }

    override fun onResume() {
        super.onResume()
        getToDoFromDatabase(date)
    }

lateinit var data:MutableList<todo>
    private fun getToDoFromDatabase(date: Date) {
         data = tododatabase.getinstance(requireContext().applicationContext).tododao().gettodobudate(date)
        todoAdapter.changedata(data)
    }

    fun refreshData() {
        getToDoFromDatabase(date)
    }
    fun onitemclicktobeupdated(){
        todoAdapter.onitemclicktoupdate=object:todoAdapter.onitemclicked{
            override fun onitemclicktoupdate(todo: todo) {
                var intent:Intent=Intent(requireContext(),updatetodoactivity::class.java)
                intent.putExtra("todo",todo )
                startActivity(intent)
            }

            override fun onitemclicktobedeleted(position: Int) {
                val todo=data.get(position)
                data.removeAt(position)
                todoAdapter.notifyItemRemoved(position)
                todoAdapter.notifyDataSetChanged()
                tododatabase.getinstance(requireContext()).tododao().delettodo(todo)
                Toast.makeText(requireContext(), "item removed", Toast.LENGTH_SHORT).show()

            }

        }

    }
}

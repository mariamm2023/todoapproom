package com.example.todoapproom.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.example.todoapproom.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addactionbutton:FloatingActionButton
    lateinit var listfragment:todolistfragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView=findViewById(R.id.button_navigaton_view)

        addactionbutton=findViewById(R.id.addbutton)
        bottomNavigationView.setOnItemSelectedListener {item->
            if(item.itemId==R.id.navigation_list){
                listfragment= todolistfragment()
                pushfragmenr(listfragment)

            }else{
                pushfragmenr(settingsfragment())

            }
            return@setOnItemSelectedListener true
        }
        bottomNavigationView.selectedItemId= R.id.navigation_list
        addactionbutton.setOnClickListener {
            showaddbuttomsheet()
        }
    }

    private fun showaddbuttomsheet() {
        val buttomsheetfragment=addtodobuttomsheet()
        buttomsheetfragment.show(supportFragmentManager,"")
        buttomsheetfragment.onaddfinishlistene=object:addtodobuttomsheet.onaddfinishlistener{
            override fun onfinish() {
                listfragment.refreshData()

            }
        }




    }

    fun pushfragmenr(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()

    }
}
package com.example.todoapproom.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapproom.R
import com.example.todoapproom.database.model.todo
import com.zerobranch.layout.SwipeLayout


class todoAdapter(var item:List<todo>?=null):RecyclerView.Adapter<todoAdapter.viewHolder>() {
    class viewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val title:TextView=itemview.findViewById(R.id.title)
        val desc:TextView=itemview.findViewById(R.id.desc)
        val markdone:ImageView=itemview.findViewById(R.id.markasdone)
       val  rightView :ImageView=itemview.findViewById(R.id.right_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
    val view =LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int =item?.size?:0



    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       val item=item?.get(position)
        holder.title.setText(item?.name)
        holder.desc.setText(item?.description)
        if(onitemclicktoupdate!=null){
            holder.itemView.setOnClickListener (View.OnClickListener {//بعمل click عند الهولدر
                if (item != null) {
                    onitemclicktoupdate?.onitemclicktoupdate(item)
                }

            })
            holder.rightView.setOnClickListener (View.OnClickListener {
                onitemclicktoupdate?.onitemclicktobedeleted(position)
            })


        }


    }
    fun changedata(newitems:List<todo> ){
        item=newitems
        notifyDataSetChanged()

    }
    var onitemclicktoupdate:onitemclicked?=null
    interface onitemclicked{
        fun onitemclicktoupdate(todo:todo)//هترجعلي ايه
        fun onitemclicktobedeleted(position: Int)

    }
}
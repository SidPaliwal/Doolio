package com.example.doolio

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.doolio.Data.Word
import kotlinx.android.synthetic.main.custom_row.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.myViewHolder>() {


    private var wordList:List<Word> = emptyList<Word>()

    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val wordlist = wordList[position]
        holder.itemView.custom_title.setText(wordlist.Title)
        holder.itemView.custom_description.setText(wordlist.Description)
        val df: DateFormat = SimpleDateFormat("EEE, d MMM yyyy, HH:mm")
        val date: String = df.format(Calendar.getInstance().getTime())
        holder.itemView.custom_date.text = date
        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(wordlist)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {

        return wordList.size
    }
    fun setData(user: List<Word>){
        this.wordList = user
        notifyDataSetChanged()
    }
    interface onWordClick{

        fun OnNoteClick(position:Int)

    }
}
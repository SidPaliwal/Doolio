package com.example.doolio

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.doolio.Data.Word
import com.example.doolio.Data.WordViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mWordViewModel:WordViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mWordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        view.add_button.setOnClickListener {
            inputData()
        }



        return view
    }

    private fun inputData(){
        val title = add_title.text.toString()
        val description = add_description.text.toString()
        if(inputcheck(title,description)){

            val word = Word(
                0,title,description
            )

            mWordViewModel.addWord(word)

            Toast.makeText(requireContext(),"Successfully added note",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)


        }
        else{
            Toast.makeText(requireContext(),"Enter all fields",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputcheck(title:String, description:String):Boolean{

        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))

    }


}
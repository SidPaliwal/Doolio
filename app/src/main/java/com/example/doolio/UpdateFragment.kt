package com.example.doolio

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.doolio.Data.Word
import com.example.doolio.Data.WordViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mWordViewModel: WordViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)
        mWordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        view.update_title.setText(args.currentWord.Title)
        view.update_description.setText(args.currentWord.Description)
        view.update_button.setOnClickListener {
            updateDatabase()
        }


        return view
    }

    private fun updateDatabase(){
        val title = update_title.text.toString()
        val description = update_description.text.toString()

        if(inputcheck(title,description)){
            val word: Word = Word(args.currentWord.id,title,description)
            mWordViewModel.updateWord(word)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"fill all fields",Toast.LENGTH_LONG).show()
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.delete_option){
            deleteData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteData() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){
            _,_->
            mWordViewModel.DeleteNote(args.currentWord)
        }
        builder.setNegativeButton("No"){
                _,_->
        }
        builder.setTitle("DELETE ${args.currentWord.Title}")
        builder.setMessage("are u sure u want to delete ${args.currentWord.Title}")
        builder.create().show()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_delete,menu)
    }

    private fun inputcheck(title:String, description:String):Boolean{

        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))

    }


}
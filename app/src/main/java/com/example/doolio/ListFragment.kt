package com.example.doolio

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.doolio.Data.WordViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import java.util.*


class ListFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var mWordViewModel: WordViewModel
    private lateinit var adapter:ListAdapter


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        val adapter = ListAdapter()
        val recyclerview = view.recyclerView
        recyclerview.adapter = adapter
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

// setting recycler view layout to staggered grid


// setting recycler view layout to staggered grid

        recyclerview.layoutManager = staggeredGridLayoutManager

        mWordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        mWordViewModel.readAllData.observe(viewLifecycleOwner, { Word ->
            adapter.setData(Word)
        })



        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchview = search.actionView as? SearchView
        searchview?.isSubmitButtonEnabled = true
        searchview?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.delete_search_option){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {

        mWordViewModel.deleteAll()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

}
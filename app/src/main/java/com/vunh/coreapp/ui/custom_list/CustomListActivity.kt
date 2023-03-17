package com.vunh.coreapp.ui.custom_list

import android.R
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vunh.coreapp.BaseActivity
import com.vunh.coreapp.databinding.ActivityCustomListBinding
import com.vunh.coreapp.ui.custom_list.adapter.CustomGridAdapter
import com.vunh.coreapp.ui.custom_list.adapter.CustomListViewAdapter
import com.vunh.coreapp.ui.custom_list.adapter.CustomRecyclerViewAdapter
import com.vunh.coreapp.ui.custom_list.models.CustomGridViewModel
import com.vunh.coreapp.ui.custom_list.models.CustomListModel


class CustomListActivity : BaseActivity() {
    private lateinit var binding: ActivityCustomListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        baseStringList()
        customAdapterListView()
        customAdapterRecyclerView()
        customAdapterGridView()
    }

    private fun baseStringList() {
        val baseStringList = arrayListOf<String>()
        for (i in 0 until 10) {
            baseStringList.add("Item $i")
        }
        val arrayAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, baseStringList)
        binding.baseStringListLv.adapter = arrayAdapter
        binding.baseStringListLv.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "$i", Toast.LENGTH_SHORT).show()
        }
    }

    private fun customAdapterListView() {
        val customAdapterList = arrayListOf<CustomListModel>()
        for (i in 0 until 10) {
            customAdapterList.add(CustomListModel(title = "title $i", desc = "desc $i"))
        }
        val customListViewAdapter = CustomListViewAdapter(this, customAdapterList)
        binding.customAdapterLv.apply {
            adapter = customListViewAdapter
        }
        binding.customAdapterLv.setOnItemClickListener { adapterView, view, i, l ->
            val obj = adapterView.getItemAtPosition(i) as CustomListModel
            Toast.makeText(this, "${obj.title} - ${obj.desc}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun customAdapterRecyclerView() {
        val customAdapterList = arrayListOf<CustomListModel>()
        for (i in 0 until 10) {
            customAdapterList.add(CustomListModel(title = "title $i", desc = "desc $i"))
        }
        val customListViewAdapter = CustomRecyclerViewAdapter {
            Toast.makeText(this, "${it.title} - ${it.desc}", Toast.LENGTH_SHORT).show()
        }
        binding.customAdapterRv.apply {
            layoutManager = LinearLayoutManager(context)
            customListViewAdapter.submitList(customAdapterList)
            adapter = customListViewAdapter
        }
    }

    private fun customAdapterGridView() {
        val customAdapterList = arrayListOf<CustomGridViewModel>()
        customAdapterList.add(CustomGridViewModel(title = "title o7planning", desc = "o7planning", url = "http://o7planning.org"))
        customAdapterList.add(CustomGridViewModel(title = "title Google", desc = "Google ", url = "http://google.com"))
        customAdapterList.add(CustomGridViewModel(title = "title Facebook", desc = "Facebook ", url = "http://facebook.com"))
        customAdapterList.add(CustomGridViewModel(title = "title Eclipse", desc = "Eclipse ", url = "http://eclipse.org"))
        val customListViewAdapter = CustomGridAdapter(this, customAdapterList)
        binding.customAdapterGv.apply {
            adapter = customListViewAdapter
        }
        binding.customAdapterGv.setOnItemClickListener { adapterView, view, i, l ->
            val obj = adapterView.getItemAtPosition(i) as CustomGridViewModel
            Toast.makeText(this, "${obj.title} - ${obj.desc} - ${obj.url}", Toast.LENGTH_SHORT).show()
        }
    }
}
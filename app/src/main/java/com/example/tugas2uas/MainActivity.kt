package com.example.tugas2uas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas2uas.db.DebtDao
import com.example.tugas2uas.db.DebtRoomDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: DebtRoomDatabase
    private lateinit var dao: DebtDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        fab_add.setOnClickListener {
            startActivity(Intent(Intent(this, CreateUpdateActivity::class.java)))
        }
    }

    private fun getData() {
        database = DebtRoomDatabase.getDatabase(this)
        dao = database.debtDao()

        val items = dao.getAll()

        val adapter = DebtAdapter(items)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerview_main.adapter = adapter
        recyclerview_main.layoutManager = layoutManager

        if (items.isNotEmpty()) {
            text_view_debt_empty.visibility = View.GONE
        } else {
            text_view_debt_empty.visibility = View.VISIBLE
        }

    }

    override fun onResume() {
        getData()
        super.onResume()
    }
}
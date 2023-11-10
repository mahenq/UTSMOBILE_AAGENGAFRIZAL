package com.example.film

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCover: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCover = findViewById(R.id.rv_cover)
        rvCover.setHasFixedSize(true)
        list.addAll(getListCover())
        showRecyclerList()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about-> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getListCover(): ArrayList<Film> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataKeterangan = resources.getStringArray(R.array.data_keterangan)  // Tambahkan ini

        val listDestination = ArrayList<Film>()
        for (i in dataName.indices) {
            val destination = Film(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1),dataKeterangan[i]) // Tambahkan keterangan)
            listDestination.add(destination)
        }
        return listDestination
    }

    private fun showRecyclerList() {
        rvCover.layoutManager = LinearLayoutManager(this)
        val listDestinationAdapter = ListFilmAdapter(list)
        rvCover.adapter = listDestinationAdapter

        listDestinationAdapter.setOnItemClickCallback(object : ListFilmAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Film) {
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra("photo", data.photo)
                detailIntent.putExtra("title", data.name)
                detailIntent.putExtra("description", data.description)
                detailIntent.putExtra("keterangan", data.keterangan) // Tambahkan baris ini
                //detailIntent.putExtra("keterangan", data.keterangan)
                startActivity(detailIntent)
            }
        })
    }

}
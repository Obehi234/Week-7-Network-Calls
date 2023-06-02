package com.example.week7_getpokemon.activities


import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.week7_getpokemon.ApiService
import com.example.week7_getpokemon.R
import com.example.week7_getpokemon.RetrofitHelper
import com.example.week7_getpokemon.adapter.PokemonAdapter
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private var progressDialog: ProgressDialog? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

         recyclerView =  findViewById(R.id.recyclerView)


        getPokemon()
    }

    private fun getPokemon() {
        lifecycleScope.launch {
            showLoading("Getting, please wait ...")

            val result = apiService.getPokemon()
            if(result.isSuccessful) {
                if(result.body() != null){
                    adapter = PokemonAdapter(result.body()?.results ?: listOf(), object : PokemonAdapter.OnItemClickListener {
                        override fun onItemClick(pokemon: com.example.week7_getpokemon.model.Result) {
                            val bundle = Bundle()
                            bundle.putString("name", pokemon.name)
                            bundle.putString("url", pokemon.url)
                            val intent = Intent(this@MainActivity, PokemonDetailsActivity::class.java)
                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                    })
                    recyclerView.adapter = adapter



                    Log.d("Pokemon Generation: ", "getPokemonGeneration ${result.body()}")
            } else {
                Log.e("Pokemon Generation", "getPokemonGeneration failed ${result.message()}")
            }
            progressDialog?.dismiss()
        }
    }


}

    private fun showLoading(msg: String) {
        progressDialog = ProgressDialog.show(this, null, msg, true)

    }

    }

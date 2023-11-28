package com.example.comics.zig.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comics.databinding.ActivityMainBinding
import com.example.comics.zig.interactor.Interactor
import com.example.comics.zig.presenter.Presenter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), IView {

    private val interactor: Interactor = Interactor(Presenter(this))

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        refresh()

        swipeList()

        Toast.makeText(this,"Click para ir ao Compose", Toast.LENGTH_LONG).show()
    }

    private fun swipeList() = with(binding?.swipeRefresh) {
        this?.setOnRefreshListener {
            refresh()
        }
    }

    override fun refresh() {
        with(binding) {
            this?.swipeRefresh?.isRefreshing = true
            lifecycle.coroutineScope.launch {
                interactor.getComics()
            }
        }
    }

    override fun viewList(list: List<ItemVO>) {
        with(binding) {
            this?.errorTV?.visibility = View.GONE
            this?.listItem?.visibility = View.VISIBLE
            this?.listItem?.adapter = Adapter(list)
            this?.listItem?.layoutManager = LinearLayoutManager(this@MainActivity)
            this?.swipeRefresh?.isRefreshing = false
        }
    }

    override fun error() {
        with(binding) {
            this?.listItem?.visibility = View.GONE
            this?.errorTV?.visibility = View.VISIBLE
            this?.swipeRefresh?.isRefreshing = false
        }
    }

}
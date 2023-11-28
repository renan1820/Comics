package com.example.comics.zig.presenter

import com.example.comics.zig.repository.ItemModel
import com.example.comics.zig.view.IView
import com.example.comics.zig.view.ItemVO

class Presenter(private val iview: IView) : IPresenter {

    override fun setupList(list: ItemModel) {
        iview.viewList(
            list = list.data.results.map {
                ItemVO(
                    image = "${it.thumbnail.path}.${it.thumbnail.extension}",
                    title = it.title,
                    subtitle = it.description ?: "Sem descricao"
                )
            }
        )
    }

    override fun error() {
        iview.error()
    }
}
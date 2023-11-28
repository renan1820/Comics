package com.example.comics.zig.presenter

import com.example.comics.zig.repository.ItemModel


interface IPresenter {

    fun setupList(list: ItemModel)

    fun error()
}
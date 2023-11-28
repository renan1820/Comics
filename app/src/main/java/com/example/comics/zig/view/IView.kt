package com.example.comics.zig.view

interface IView {

    fun viewList(list: List<ItemVO>)

    fun refresh()

    fun error()

}
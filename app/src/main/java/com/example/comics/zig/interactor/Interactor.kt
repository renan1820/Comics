package com.example.comics.zig.interactor

import com.example.comics.zig.presenter.IPresenter
import com.example.comics.zig.repository.Repository
import com.example.comics.zig.util.safeRunDispatcher
import com.example.comics.zig.util.Result.Success
import com.example.comics.zig.util.Result.Failure

class Interactor(
    private val iPresenter: IPresenter,
    private val repository: Repository = Repository()
) : IInteractor {


    override suspend fun getComics() {
        when (val result = safeRunDispatcher {
            repository.getComics()
        }) {
            is Success -> iPresenter.setupList(result.data)
            is Failure -> iPresenter.error()
        }
    }

}
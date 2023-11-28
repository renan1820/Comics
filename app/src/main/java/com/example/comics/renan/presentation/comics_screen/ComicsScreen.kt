package com.example.comics.renan.presentation.comics_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comics.renan.domain.model.Comic
import com.example.comics.renan.presentation.comics_screen.state.ComicsScreenEvent
import com.example.comics.renan.presentation.comics_screen.state.ComicsScreenViewModel
import com.example.comics.renan.presentation.comics_screen.state.ComicsState
import com.example.comics.renan.presentation.component.ComicCardItem
import com.example.comics.renan.presentation.component.RetryContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComicsScreen(state: ComicsState, onEvent:(ComicsScreenEvent) -> Unit, viewModel: ComicsScreenViewModel){

    val pullRefreshState = rememberPullRefreshState(state.swipeRefresh, viewModel::getComicsSwiper)

    Box(Modifier.pullRefresh(pullRefreshState)) {
        Column {
            ComicsList(
                state = state,
                onCardClicked = { comic ->
                    onEvent(ComicsScreenEvent.onComicsCardClicked(comic = comic))
                },
                onRetry = {
                    state.selectedComics?.let { ComicsScreenEvent.onComicsCardClicked(it) }
                        ?.let { onEvent(it) }
                })
        }
        PullRefreshIndicator(state.swipeRefresh, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun ComicsList(state: ComicsState,
               onCardClicked:(Comic) -> Unit,
               onRetry: () -> Unit
){

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
        ){
       items(state.comicsList){ comic ->
           ComicCardItem(comic = comic, onCardClicked = onCardClicked)
       }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        if (state.isLoading){
            CircularProgressIndicator()
        }
        if (state.error != null){
            RetryContent(
                error= state.error,
                onRetry= onRetry
            )
        }
    }
}

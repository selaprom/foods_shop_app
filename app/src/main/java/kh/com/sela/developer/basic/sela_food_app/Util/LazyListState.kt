package kh.com.sela.developer.basic.sela_food_app.Util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState


@Composable
fun rememberBottomBarVisibility(
    listState: LazyGridState
): State<Boolean> {

    var isVisible by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(listState) {
        var previousIndex = 0
        var previousOffset = 0

        snapshotFlow {
            listState.firstVisibleItemIndex to
                    listState.firstVisibleItemScrollOffset
        }.collect { (index, offset) ->

            val scrollingUp =
                index > previousIndex ||
                        (index == previousIndex && offset > previousOffset)

            val scrollingDown =
                index < previousIndex ||
                        (index == previousIndex && offset < previousOffset)

            when {
                scrollingUp -> isVisible = false
                scrollingDown -> isVisible = true
            }

            // Always show at the top
            if (index == 0 && offset == 0) {
                isVisible = true
            }

            previousIndex = index
            previousOffset = offset
        }
    }

    return rememberUpdatedState(isVisible)
}
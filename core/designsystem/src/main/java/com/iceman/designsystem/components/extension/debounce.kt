package com.iceman.designsystem.components.extension
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun  <T> debounce(delayMillis: Long = 300L, onDebounced: (T) -> Unit): (T) -> Unit {

    var debounceJob by remember { mutableStateOf<Job?>(null) }
    return {param:T ->
        debounceJob?.cancel()
        debounceJob = CoroutineScope(Dispatchers.Default).launch{
            delay(delayMillis)
            onDebounced
        }
    }
}
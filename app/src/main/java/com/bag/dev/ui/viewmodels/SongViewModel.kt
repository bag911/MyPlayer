package com.bag.dev.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bag.dev.player.MusicService
import com.bag.dev.player.MusicServiceConnection
import com.bag.dev.utils.Constants.UPDATE_INTERVAL
import com.bag.dev.utils.currentPlaybackPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    musicServiceConnection: MusicServiceConnection
) : ViewModel() {

    private val playbackState = musicServiceConnection.playbackState

    private val _currentSongDuration = MutableLiveData<Long>()
    val currentSongDuration: LiveData<Long>
        get() = _currentSongDuration

    private val _currentPlayerPosition = MutableLiveData<Long>()
    val currentPlayerPosition: LiveData<Long>
        get() = _currentPlayerPosition

    init {
        updateCurrentPosition()
    }

    private fun updateCurrentPosition() {
        viewModelScope.launch {
            while (true) {
                val position = playbackState.value?.currentPlaybackPosition ?: 0
                if (currentPlayerPosition.value != position) {
                    _currentPlayerPosition.postValue(position)
                    _currentSongDuration.postValue(MusicService.currentSongDuration)
                }
                delay(UPDATE_INTERVAL)
            }
        }
    }
}

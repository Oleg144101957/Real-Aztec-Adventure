package com.arbtemey.solo.aztecgold2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arbtemey.solo.aztecgold2.domain.AztecElement
import com.arbtemey.solo.aztecgold2.domain.AztecMemoryStorage
import com.arbtemey.solo.aztecgold2.domain.GameStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AztecViewModel @Inject constructor(private val storage: AztecMemoryStorage) : ViewModel() {

    private val _userName: MutableLiveData<String> = MutableLiveData(storage.readGamerName())
    val userName: LiveData<String> = _userName

    private val _scores: MutableLiveData<Int> = MutableLiveData(0)
    val scores: LiveData<Int> = _scores

    private val _status: MutableLiveData<GameStates> = MutableLiveData(GameStates.PLAY_STATE)
    val status: LiveData<GameStates> = _status

    private val _liveElements: MutableLiveData<List<AztecElement>> = MutableLiveData(listOf(
        AztecElement(id = 0, image = R.drawable.a1),
        AztecElement(id = 1, image = R.drawable.a2),
        AztecElement(id = 2, image = R.drawable.a3),
        AztecElement(id = 3, image = R.drawable.a4),
        AztecElement(id = 4, image = R.drawable.a5),
        AztecElement(id = 5, image = R.drawable.a6),
        AztecElement(id = 6, image = R.drawable.a7),
    ))
    val liveElements: LiveData<List<AztecElement>> = _liveElements

    fun onClickButton(id: Int){
        val updatedList = _liveElements.value?.map{
            if (it.id == id){
                it.copy(isVisible = false)
            } else {
                it
            }
        }


        changeScores(id)
        _liveElements.postValue(updatedList)

        checkElements()
    }

    fun postStatus(gameStatus: GameStates){
        if (_status.value == GameStates.PLAY_STATE){
            _status.value = gameStatus
        }
    }

    fun saveName(name: String){
        storage.saveGamerName(name)
    }

    private fun checkElements() {
        viewModelScope.launch {
            delay(300)
            val isAllInvisible = _liveElements.value?.all { !it.isVisible } ?: false
            if (isAllInvisible || _scores.value == 6){
                val newVisibleList = _liveElements.value?.map { it.copy(isVisible = true) }
                _liveElements.value = null
                delay(1000)
                _liveElements.value = newVisibleList
            }
        }
    }

    private fun changeScores(id: Int){
        val currentScores = _scores.value ?: 0

        when(id){
            0 -> { postScores(currentScores+1) }
            1 -> { postScores(currentScores+1) }
            2 -> { postScores(currentScores-5) }
            3 -> { postScores(currentScores+1) }
            4 -> { postScores(currentScores+1) }
            5 -> { postScores(currentScores+1) }
            6 -> { postScores(currentScores-7) }
        }
    }

    private fun postScores(newScores: Int){
        _scores.value = newScores
    }

    fun initListOfElements(){
        _scores.value = 0
        _status.value = GameStates.PLAY_STATE
        _userName.value = storage.readGamerName()
        _liveElements.value = _liveElements.value?.map { it.copy(isVisible = true) }
    }
}
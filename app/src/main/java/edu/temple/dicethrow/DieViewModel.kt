package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DieViewModel: ViewModel() {
    val currentRoll : MutableLiveData<Int> = MutableLiveData()
}
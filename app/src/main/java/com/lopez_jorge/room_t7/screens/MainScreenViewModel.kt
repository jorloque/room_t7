package com.lopez_jorge.room_t7.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel() : ViewModel() {
    private val _taskName = MutableLiveData<String>()
    val taskName: LiveData<String> = _taskName

    fun onTaskNameChange(taskName: String) {
        _taskName.value = taskName
    }

    fun onTaskNameDelete() {
        _taskName.value = ""
    }
}
package com.example.aicvirtualtour.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aicvirtualtour.data.AICRepository
import com.example.aicvirtualtour.data.ResponseState
import com.example.aicvirtualtour.models.Department
import com.example.aicvirtualtour.viewModels.DepartmentListEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentListViewModel @Inject constructor(
    private val repository: AICRepository
) : ViewModel() {

    private val _responseState: MutableLiveData<ResponseState<List<Department>>> = MutableLiveData()
    val responseState: LiveData<ResponseState<List<Department>>>
        get() = _responseState

    fun performEvent(departmentListEvent: DepartmentListEvent) {
        viewModelScope.launch {
            when(departmentListEvent) {
                is GetDepartments -> {
                    repository.getDepartments().onEach { responseState ->
                        _responseState.value = responseState
                    }.launchIn(viewModelScope)
                }

                is None -> {}
            }
        }
    }
}


sealed class DepartmentListEvent {
    object GetDepartments: DepartmentListEvent()

    object None: DepartmentListEvent()
}
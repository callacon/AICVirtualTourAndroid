package com.example.aicvirtualtour.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aicvirtualtour.data.AICDataSource
import com.example.aicvirtualtour.models.Department
import com.example.aicvirtualtour.models.Departments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DepartmentListViewModel @Inject constructor(
    private val dataSource: AICDataSource
) : ViewModel() {
    fun departments() = performNetworkCall<List<Department>>(dataSource.getDepartments())

    private fun <T> performNetworkCall(call: suspend () -> Response<T>) = viewModelScope.launch {
        loading()
        try {
            val response = call()
            if (response.isSuccessful && response.body() != null) {
                success(response.body())
            }
        } catch (e: Exception) {
            error(e.message.toString())
        }

    }

    fun <T> success(data: T): T {

    }

    fun loading() {

    }

    fun error(message: String): String {
        return message
    }
}
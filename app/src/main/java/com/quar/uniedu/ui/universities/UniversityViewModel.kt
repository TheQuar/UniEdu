package com.quar.uniedu.ui.universities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quar.uniedu.network.Resource
import com.quar.uniedu.network.ResponseCloud
import kotlinx.coroutines.launch

class UniversityViewModel(private val universityRepository: UniversityRepository) : ViewModel() {
    constructor() : this(universityRepository = UniversityRepository())

    private val _university = MutableLiveData<Resource<ResponseCloud>>()
    val university: LiveData<Resource<ResponseCloud>>
        get() = _university

    fun getUniversity() {
        viewModelScope.launch {
            _university.postValue(universityRepository.getUniversities())
        }
    }
}
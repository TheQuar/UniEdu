package com.quar.uniedu.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quar.uniedu.network.Resource
import com.quar.uniedu.network.ResponseCloud
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    constructor() : this(LoginRepository())

    private val _login = MutableLiveData<Resource<ResponseCloud>>()
    val login: LiveData<Resource<ResponseCloud>>
        get() = _login

    private val _signup = MutableLiveData<Resource<ResponseCloud>>()
    val signup: LiveData<Resource<ResponseCloud>>
        get() = _signup


    fun login(username: String, password: String) {
        viewModelScope.launch {
            _login.postValue(repository.login(username, password))
        }
    }

    fun signup(
        firstname: String,
        lastname: String,
        email: String,
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            _signup.postValue(repository.signup(firstname, lastname, email, username, password))
        }
    }
}
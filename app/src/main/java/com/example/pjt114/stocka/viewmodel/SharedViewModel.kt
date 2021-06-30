package com.example.pjt114.stocka.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pjt114.stocka.model.User
import com.example.pjt114.stocka.repository.AppRepository
import kotlinx.coroutines.launch

class SharedViewModel( val appRepository: AppRepository): ViewModel() {

    var fullName: String = ""
    var phoneNumber: String = ""
    var emailAddress: String =""
    var businessIndustry: String =""
    var userName: String =""
    var password : String = ""

    init {
        getUser()
    }

    fun saveFullName(name:String){
        fullName = name
    }

    fun savePhoneNumber(number: String){
        phoneNumber = number
    }
    fun saveEmailAddress(email:String){
        emailAddress = email
    }
    fun saveIndustry(industry :String){
         businessIndustry =industry
    }

    fun saveUsername(uName: String){
        userName = uName
    }

    fun savePassword(pass: String){
        password = pass
    }

    fun getUser() = appRepository.getUsers()

   fun insertNewUser(user: User) = viewModelScope.launch {
       appRepository.insertNewUser(user)
   }


}
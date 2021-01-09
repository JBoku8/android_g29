package com.example.java_and_kotlin_g29

class User(var firstName: String, var lastName: String, var age: Int){

    fun getInfo(): String{
        return  "$firstName $lastName, $age"
    }
}
package ru.uniquenature.myapplication.check

sealed class VerifyResult {
    object Success : VerifyResult()
    object Error : VerifyResult()
}
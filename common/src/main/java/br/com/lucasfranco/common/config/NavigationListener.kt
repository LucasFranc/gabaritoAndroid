package br.com.lucasfranco.common.config

interface NavigationListener {
    fun onBackButtonClick()
    fun requestPermissions(permision: List<String>, codePermisionRequest: Int)
}
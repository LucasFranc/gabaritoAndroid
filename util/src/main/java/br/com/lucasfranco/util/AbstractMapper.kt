package br.com.lucasfranco.util

interface AbstractMapper<in PARAMETER, out RESULT> {
    fun map(param: PARAMETER): RESULT
}
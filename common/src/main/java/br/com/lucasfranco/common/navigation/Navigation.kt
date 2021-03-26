package br.com.lucasfranco.common.navigation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

interface Navigation {
    fun navigationToFaqChatActivity(context: Context, tag: String)
}
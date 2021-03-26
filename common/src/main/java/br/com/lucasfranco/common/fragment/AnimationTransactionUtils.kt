package br.com.lucasfranco.common.fragment

import androidx.annotation.AnimRes
import br.com.lucasfranco.common.R

object AnimationTransactionObject {

    @AnimRes
    var enterFragment: Int = R.anim.cross_navigation_pop_enter_slide_up

    @AnimRes
    var exitFragment: Int = R.anim.cross_navigation_pop_exit_slide_up

    @AnimRes
    var popEnter: Int = R.anim.cross_navigation_enter_slide_down

    @AnimRes
    var popExit: Int = R.anim.cross_navigation_exit_slide_down
}
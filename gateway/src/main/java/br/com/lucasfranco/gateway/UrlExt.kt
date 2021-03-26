package br.com.lucasfranco.gateway

import br.com.lucasfranco.util.BuildTypes


fun getUrlByBuildType(): URLs =
    when (BuildConfig.BUILD_TYPE) {
        BuildTypes.DEBUG.value -> URLs.URL_POKE_API_DEV
        BuildTypes.HOMOL.value -> URLs.URL_POKE_API_HML
        BuildTypes.RELEASE.value -> URLs.URL_POKE_API_PRD
        else -> URLs.URL_POKE_API_DEV
    }
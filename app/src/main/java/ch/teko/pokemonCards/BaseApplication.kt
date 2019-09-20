package ch.teko.pokemonCards

import android.app.Application
import ch.teko.pokemonCards.networking.RestApi

/**
 * The base application is used to setup some basic app stuff like the api or db.
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Build our api instance
        RestApi.Client.build()
    }

}
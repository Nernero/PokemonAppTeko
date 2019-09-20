package ch.teko.pokemonCards.networking

import ch.teko.pokemonCards.model.Json4Kotlin_Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This interface is used by Retrofit to generate the internal client.
 *
 * For more information go to: https://randomuser.me/documentation
 */
interface Api {

//    @GET("api/v2/pokemon/")
    @GET("{id}")
fun fetchRandomCard(
    @Path("id") id: String
): Call<Json4Kotlin_Base>
//    fun fetchRandomCard(@Query("number") Integer number): Call<Json4Kotlin_Base>



}
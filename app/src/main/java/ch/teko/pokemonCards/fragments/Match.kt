package ch.teko.pokemonCards.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.teko.pokemonCards.R
import ch.teko.pokemonCards.networking.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import ch.teko.pokemonCards.model.Json4Kotlin_Base
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropSquareTransformation
import kotlinx.android.synthetic.main.fragment_cardselector.*
import java.io.OutputStreamWriter

class Match : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cardselector, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomPokemon()
        }


    fun getRandomPokemon(){
        //Request a random user from the backend
        RestApi.Client.getInstance().fetchRandomCard((0..807).random().toString()) //(0..807).random().toString()
            .enqueue(object: Callback<Json4Kotlin_Base> {

                override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {
                    //add an error toast
                    Toast.makeText(getActivity(), "Auto refresh...", Toast.LENGTH_LONG).show()
                    getRandomPokemon()
                }



                override fun onResponse(call: Call<Json4Kotlin_Base>, response: Response<Json4Kotlin_Base>) {
                    if (response.isSuccessful) {

                        response.body()?.sprites?.apply {

                            Picasso.get().load(this.front_default)
                                .transform(CropSquareTransformation())
                                .into(user_img)
                        }


                        response.body()?.name?.apply {
                            match_name.text = "${this}"
                        }
                        response.body()?.types?.apply {
                            match_type.text = "${this[0].type.name}"
                        }

                        response.body()?.forms?.apply {
                            match_form.text = "${this[0].name}"
                        }

                            response.body()?.abilities?.apply {

                                match_ability.text = this[0].ability.name

                            like.setOnClickListener{
                                val jsonUser = Gson().toJson(response.body())
                                val outputStreamWriter =
                                    OutputStreamWriter(context?.openFileOutput("pokemon.txt", Context.MODE_APPEND))
                                outputStreamWriter.write(jsonUser+"\n")
                                outputStreamWriter.close()

                                getRandomPokemon()
                            }

                            dislike.setOnClickListener{
                                getRandomPokemon()
                            }
                        }
                    } else {
                        Toast.makeText(
                            activity, "Error while fetching and displaying Data!",
                            Toast.LENGTH_LONG).show()
                    }
                }
            })
    }



}

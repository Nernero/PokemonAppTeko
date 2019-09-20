package ch.teko.pokemonCards.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ch.sik.teko.home.adapter.UserAdapter
import ch.teko.pokemonCards.R
import ch.teko.pokemonCards.model.Json4Kotlin_Base
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_mydecklist.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStream


class MyDeckList : Fragment(), UserAdapter.AdapterCallback {

    private val adapter = UserAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mydecklist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set the adapter
        match_list.layoutManager = LinearLayoutManager(view.context)
        match_list.adapter = adapter



        val gson = Gson()

        //auslesen der text Datei

        //serialisieren in objekt

        //jedes objekt der liste im adapter hinzufügen

        //fertig...

        //so ca in der richtung
//        val allCatchedPokemon = context?.getFileStreamPath("pokemon.txt")?.bufferedReader()
//            ?.readLines()?.map { gson.fromJson(it, User::class.java) }?.toMutableList()
//
//        allCatchedPokemon?.let { adapter.setData(it) }




        //Funktioniert nicht...
//        val inputStream: InputStream = File("example.txt").inputStream()
//        val lineList = mutableListOf<String>()
//
//        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

        //Funktioniert auch nicht
//        val jsonString: String = File("pokemon.txt").readText()
//        val editablrString = "[$jsonString]"

        //funktioniert auch nicht -.-
//        val lineList = mutableListOf<String>()
//        File("example.txt").useLines { lines -> lines.forEach { lineList.add(it) }}

//        val objectList = gson.fromJson(editablrString, Array<Json4Kotlin_Base>::class.java).toMutableList()
//
//
//        objectList.let {
//            if (it != null) {
//                adapter.setData(it)
//            }
//        }
    }

    /**
     * Callback for the adapter
     */
    override fun onItemClicked(user: Json4Kotlin_Base, position: Int) {

    }
}

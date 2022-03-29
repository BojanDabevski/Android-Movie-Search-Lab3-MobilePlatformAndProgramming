package mk.finki.ukim.lab3omdbapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mk.finki.ukim.lab3omdbapi.Adapter.MovieAdapter
import mk.finki.ukim.lab3omdbapi.Api.OmdbApi
import mk.finki.ukim.lab3omdbapi.Api.OmdbApiClient
import mk.finki.ukim.lab3omdbapi.Models.MovieResult
import mk.finki.ukim.lab3omdbapi.Models.SearchResult

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {

    private lateinit var omdbApiClient: OmdbApi
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewAdapter = MovieAdapter(mutableListOf<MovieResult>())

        omdbApiClient = OmdbApiClient.getOmdbApi()!!

        movieRecyclerView = view.findViewById(R.id.allMoviesRecyclerView)
        movieRecyclerView.layoutManager = LinearLayoutManager(activity)
        movieRecyclerView.adapter = recyclerViewAdapter

        val movieSearchBar: EditText? = view.findViewById(R.id.movieSearchInput)

        movieSearchBar?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                val searchQuery: String = movieSearchBar.text.toString()
                searchMovies(searchQuery)
                true
            } else {
                Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                false
            }
        }
    }

    private fun searchMovies(searchQuery: String) {
        omdbApiClient
            .getMoviesBySearch("72e48f5c", searchQuery)
            .enqueue(
                object : Callback<SearchResult> {
                    override fun onResponse(call: Call<SearchResult>?, response: Response<SearchResult>) {
                        if (response.body().Response == "False") {
                            Toast.makeText(activity, "Too many results for the app!", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            displayMovies(response.body())
                        }
                    }

                    override fun onFailure(call: Call<SearchResult>?, t: Throwable?) {
                        Toast.makeText(activity, t?.message.toString(), Toast.LENGTH_LONG).show()
                    }
                })
    }

    private fun displayMovies(movies: SearchResult) {
        recyclerViewAdapter.updateMovies(movies.Search)
    }
}
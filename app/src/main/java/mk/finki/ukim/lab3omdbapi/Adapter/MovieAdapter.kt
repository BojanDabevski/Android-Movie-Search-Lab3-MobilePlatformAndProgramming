package mk.finki.ukim.lab3omdbapi.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mk.finki.ukim.lab3omdbapi.Models.MovieResult
import mk.finki.ukim.lab3omdbapi.R


class MovieAdapter(private var allMovies: MutableList<MovieResult>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var movieImage: ImageView = view.findViewById(R.id.movieImage)
        var movieTitle: TextView = view.findViewById(R.id.movieTitle)
        var movieYear: TextView = view.findViewById(R.id.movieYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = allMovies[position]

        holder.movieTitle.text = currentMovie.Title
        holder.movieYear.text = currentMovie.Year

        Glide.with(holder.movieImage.context).load(currentMovie.Poster).into(holder.movieImage)
    }

    override fun getItemCount(): Int {
        return allMovies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(movies: MutableList<MovieResult>) {
        this.allMovies = movies
        this.notifyDataSetChanged()
    }
}
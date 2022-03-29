package mk.finki.ukim.lab3omdbapi.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

class MovieResult {
    val Title: String
    val Year: String
    val imdbID: String
    val Type: String
    val Poster: String

    constructor(Title: String, Year: String, imdbID: String, Type: String, Poster: String) {
        this.Title = Title
        this.Year = Year
        this.imdbID = imdbID
        this.Type = Type
        this.Poster = Poster
    }
}
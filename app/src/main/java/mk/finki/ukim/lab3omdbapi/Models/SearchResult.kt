package mk.finki.ukim.lab3omdbapi.Models

class SearchResult {
    val Search: MutableList<MovieResult>
    val totalResults: String
    val Response: String

    constructor(Search: MutableList<MovieResult>, totalResults: String, Response: String) {
        this.Search = Search
        this.totalResults = totalResults
        this.Response = Response
    }
}
package com.binar.listmovie

class MovieRepository {
    fun getalldata(): List<Movie>{
        return listOf (
            Movie(
                id = 0,
                judul = "Movie 1",
                deskripsi = "tes"
            ),
            Movie(
                id = 1,
                judul = "Movie 2 ",
                deskripsi = "tes 2"
            ),
            Movie(
                id = 2,
                judul = "Movie 3",
                deskripsi = "tes 3"
            ),
        )
    }
}
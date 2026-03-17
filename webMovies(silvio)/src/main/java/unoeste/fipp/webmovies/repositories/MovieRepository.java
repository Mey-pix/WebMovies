package unoeste.fipp.webmovies.repositories;

import org.springframework.stereotype.Repository;
import unoeste.fipp.webmovies.entities.Movie;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {
    private List<Movie> movies = new ArrayList<>();

    public MovieRepository() {
        movies.add(new Movie("Cidadão Kane", "1941", "Drama"));
        movies.add(new Movie("Casablanca", "1942", "Romance"));
        movies.add(new Movie("O Poderoso Chefão", "1972", "Crime"));
        movies.add(new Movie("Os Sete Samurais", "1954", "Ação"));
        movies.add(new Movie("A Noviça Rebelde", "1965", "Musical"));
        movies.add(new Movie("Psicose", "1960", "Terror"));
        movies.add(new Movie("2001: Uma Odisseia no Espaço", "1968", "Ficção Científica"));
        movies.add(new Movie("A Felicidade Não Se Compra", "1946", "Drama"));
        movies.add(new Movie("O Mágico de Oz", "1939", "Fantasia"));
        movies.add(new Movie("Laranja Mecânica", "1971", "Ficção Científica"));
        movies.add(new Movie("Taxi Driver", "1976", "Drama"));
        movies.add(new Movie("O Exorcista", "1973", "Terror"));
        movies.add(new Movie("A Lista de Schindler", "1993", "Drama"));
        movies.add(new Movie("O Silêncio dos Inocentes", "1991", "Thriller"));
        movies.add(new Movie("Doutor Jivago", "1965", "Drama"));
        movies.add(new Movie("Sangue Negro", "2007", "Drama"));
        movies.add(new Movie("O Grande Lebowski", "1998", "Comédia"));
        movies.add(new Movie("Forrest Gump", "1994", "Drama"));
        movies.add(new Movie("Caminhos Perigosos", "1953", "Crime"));
        movies.add(new Movie("Encontros e Desencontros", "2003", "Comédia Romance"));
    }

    public List<Movie> getMovies() {
        return movies;
    }
}

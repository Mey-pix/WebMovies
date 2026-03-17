package unoeste.fipp.webmovies.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.webmovies.entities.Erro;
import unoeste.fipp.webmovies.entities.Movie;
import unoeste.fipp.webmovies.repositories.MovieRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("apis")
public class MovieRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("test")
    public ResponseEntity<Object> teste(){
        return ResponseEntity.ok().build();
    }
    @GetMapping("random-movie")
    public ResponseEntity<Object> getRandomMovie(){

        Movie aleatorio = movieRepository.getMovies().get((int)(Math.random()* movieRepository.getMovies().size()));
        return ResponseEntity.ok().body(aleatorio);
    }
    @GetMapping("get-movie")
    public ResponseEntity<Object> getMovieByTitle(String title){
        Movie procurado=null;
        for(Movie movie: movieRepository.getMovies()){
            if(movie.getTitle().equalsIgnoreCase(title)){
                procurado=movie;
            }
        }
        if(procurado!=null){
            return ResponseEntity.ok().body(procurado);
        }
        return ResponseEntity.badRequest().body(new Erro("Título inexistente",""));
    }
    @GetMapping("get-movies-filter")
    public ResponseEntity<Object> getMovieByFilter(String filter){
        List <Movie> moviesList=new ArrayList<>();
        for(Movie movie: movieRepository.getMovies()){
            if(movie.getTitle().toUpperCase().contains(filter.toUpperCase())){
                moviesList.add(movie);
            }
        }
        if(!moviesList.isEmpty()){
            return ResponseEntity.ok().body(moviesList);
        }
        return ResponseEntity.badRequest().body(new Erro("Nenhum resultado",""));
    }
    @GetMapping("get-movie/{title}")
    public ResponseEntity<Object> getMovieByTitlePath(@PathVariable String title){
        Movie procurado=null;
        for(Movie movie: movieRepository.getMovies()){
            if(movie.getTitle().equalsIgnoreCase(title)){
                procurado=movie;
            }
        }
        if(procurado!=null){
            return ResponseEntity.ok().body(procurado);
        }
        return ResponseEntity.badRequest().body(new Erro("Título inexistente",""));
    }
    @PostMapping("add-movie")
    public ResponseEntity<Object> addMovie(@RequestBody Movie novo){
        if(novo.getTitle()==null || novo.getTitle().isEmpty()){
            return ResponseEntity.badRequest().body(new Erro("Informações incompletas","Complete todas as informações do filme"));
        }
        movieRepository.getMovies().add(novo);
        return ResponseEntity.ok().body(novo);
    }

    @PostMapping("add-movie-poster")
    public ResponseEntity<Object> addMoviePoster(String titulo, String ano, String genero, MultipartFile poster){
        final String UPLOAD_FOLDER = "src/main/resources/static/posters/";
        if(titulo==null || titulo.isEmpty()){
            return ResponseEntity.badRequest().body(new Erro("Informações incompletas","Complete todas as informações do filme"));
        }
        else{
            Movie novo = new Movie(titulo,ano,genero);
            if(poster!=null){
                String filename = poster.getOriginalFilename();
                try {
                    File upload = new File(UPLOAD_FOLDER);
                    if(!upload.exists()){
                        upload.mkdir();
                    }
                    poster.transferTo(new File(upload.getAbsoluteFile().getAbsolutePath()+"\\"+filename));
                    BufferedImage original = ImageIO.read(new File(upload.getAbsolutePath() + "\\" + filename));

                    int largura = 150;
                    int altura = (original.getHeight() * largura) / original.getWidth();

                    Image imagemRedimensionada = original.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

                    BufferedImage thumbnail = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

                    Graphics2D g2d = thumbnail.createGraphics();
                    g2d.drawImage(imagemRedimensionada, 0, 0, null);
                    g2d.dispose();

                    File thumbFile = new File(upload.getAbsolutePath() + "\\" + filename);
                    ImageIO.write(thumbnail, "jpg", thumbFile);
                }catch (Exception e){

                }
                novo.setPoster(filename);
            }
            movieRepository.getMovies().add(novo);
            return ResponseEntity.ok().body(novo);
        }
    }

    @GetMapping("list-movie")
    public ResponseEntity<Object> listMovies(){
        return ResponseEntity.ok(movieRepository.getMovies());
    }

    @GetMapping("list-genre/{genre}")
    public ResponseEntity<Object> listByGenre(@PathVariable String genre){

        List<Movie> moviesList = new ArrayList<>();

        for(Movie movie : movieRepository.getMovies()){
            if(movie.getCategory().equalsIgnoreCase(genre)){
                moviesList.add(movie);
            }
        }

        if(!moviesList.isEmpty()){
            return ResponseEntity.ok().body(moviesList);
        }

        return ResponseEntity.badRequest().body(new Erro("Nenhum resultado",""));
    }

    @GetMapping("list-year/{year-start}/{year-end}")
    public ResponseEntity<Object> listByYear(@PathVariable String yearStart, @PathVariable String yearEnd){
        int anoI, anoF;
        try{
            anoI=Integer.parseInt(yearStart);
            anoF=Integer.parseInt(yearEnd);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(new Erro("Ano Inválido!", ""));
        }

        List<Movie> moviesList=new ArrayList<>();
        for(Movie movie: movieRepository.getMovies()){
            int anoFilme=Integer.parseInt(movie.getYear());
            if(anoFilme >= anoI && anoFilme <= anoF){
                moviesList.add(movie);
            }
        }
        if(!moviesList.isEmpty()){
            return ResponseEntity.ok().body(moviesList);
        }
        return ResponseEntity.badRequest().body(new Erro("Nenhum resultado",""));
    }

    @GetMapping("get-generos")
    public ResponseEntity<Object> getGeneros(){
        List<Object> generosList=new ArrayList<>();
        generosList.add(new Object(){
            public final int id=1; public final String genero="Ação";
        });
        generosList.add(new Object(){
            public final int id=2; public final String genero="Aventuras";
        });
        generosList.add(new Object(){
            public final int id=3; public final String genero="Comédia";
        });
        generosList.add(new Object(){
            public final int id=4; public final String genero="Drama";
        });
        generosList.add(new Object(){
            public final int id=5; public final String genero="Terror";
        });
        generosList.add(new Object(){
            public final int id=6; public final String genero="Ficção Científica";
        });
        generosList.add(new Object(){
            public final int id=7; public final String genero="Fantasia";
        });
        generosList.add(new Object(){
            public final int id=8; public final String genero="Romance";
        });
        generosList.add(new Object(){
            public final int id=9; public final String genero="Documentário";
        });
        generosList.add(new Object(){
            public final int id=10; public final String genero="Animação";
        });
        return ResponseEntity.ok().body(generosList);
    }
}

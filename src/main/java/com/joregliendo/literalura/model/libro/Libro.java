package com.joregliendo.literalura.model.libro;

import com.joregliendo.literalura.model.autor.Autor;
import jakarta.persistence.*;


@Entity(name = "Libro")
@Table(name = "libros")

public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String titulo;

    @Enumerated(EnumType.STRING)

    private Idiomas idioma;
    private Double descarga;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String nombreAutor;

    public Libro(){}

    public Libro(DatosLibro datosLibro, Autor autor){
        this.titulo = datosLibro.titulo();
        this.autor = autor;
        this.nombreAutor=autor.getNombre();
        this.idioma = Idiomas.fromString(datosLibro.idiomas().get(0).trim());
        this.descarga = datosLibro.descarga();
    }

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.idioma = Idiomas.fromString(datosLibro.idiomas().get(0).trim());
        this.descarga = datosLibro.descarga();
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Idiomas getIdioma() {
        return idioma;
    }

    public void setIdioma(Idiomas idioma) {
        this.idioma = idioma;
    }

    public Double getDescarga() {
        return descarga;
    }

    public void setDescarga(Double descarga) {
        this.descarga = descarga;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor=autor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }


    public String toString() {
        return "\n****************   LIBRO   ****************\n" +
                "Titulo= " + titulo + '\n' +
                "Autor= " + nombreAutor + '\n' +
                "Idioma= " + idioma + '\n' +
                "Numero de descargas= " + descarga +'\n' +
                "*******************************************";
    }

}

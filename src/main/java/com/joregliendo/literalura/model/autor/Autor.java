package com.joregliendo.literalura.model.autor;

import com.joregliendo.literalura.model.libro.Libro;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Autor")
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;


    @OneToMany(mappedBy ="autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor autor) {
        this.nombre = autor.nombre();
        this.fechaDeNacimiento = autor.fechaDeNacimiento();
        this.fechaDeFallecimiento = autor.fechaDeFallecimiento();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {

        this.libros = libros;
    }

    @Override
    public String toString() {
        return "*****************  AUTOR  *****************\n" +
                "Nombre= " + nombre + '\n' +
                "Fecha de Nacimiento= " + fechaDeNacimiento + '\n' +
                "Fecha de fallecimiento= " + fechaDeFallecimiento + '\n' +
                "*******************************************";
    }

    public void autorConLibros(List<String> libros) {
        System.out.println("*****************  AUTOR  *****************\n" +
                "Nombre= " +nombre + '\n' +
                "Fecha de Nacimiento= " + fechaDeNacimiento+ '\n' +
                "Fecha de fallecimiento= " + fechaDeFallecimiento + '\n' +
                "Libros= " + libros +'\n'+
                "*******************************************");

    }
}

package com.joregliendo.literalura.principal;

import com.joregliendo.literalura.model.autor.Autor;
import com.joregliendo.literalura.model.autor.Datos;
import com.joregliendo.literalura.model.autor.DatosAutor;
import com.joregliendo.literalura.model.libro.DatosLibro;
import com.joregliendo.literalura.model.libro.Idiomas;
import com.joregliendo.literalura.model.libro.Libro;
import com.joregliendo.literalura.repository.AutorRepository;
import com.joregliendo.literalura.repository.LibroRepository;
import com.joregliendo.literalura.service.ConsumoAPI;
import com.joregliendo.literalura.service.ConvierteDatos;

import java.util.*;


public class Principal {

    private static final String URL_BASE ="https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private AutorRepository repositorioAutor;
    private LibroRepository repositorioLibro;
    private Libro libro = new Libro();
    private Autor autor = new Autor();

    public Principal(AutorRepository repository, LibroRepository repositorioLibro) {
        this.repositorioAutor=repository;
        this.repositorioLibro=repositorioLibro;
    }

    public void ejecutaMenu() {
        int opcion =-1;
        while (opcion!=0){
            var menu= """
                    ***********************************************************
                               Elija la opción deseada
                    ***********************************************************
                    1 - buscar libro por titulo
                    2 - Listar libros guardados
                    3 - Listar autores guardados
                    4 - Listar autores guardados, vivos en un determinado año
                    5 - Listar libros guardados por idiomas
                    
                    0 - Salir
                    ************************************************************
                    """;
            opcion=-1;
            System.out.println(menu);
            try {

                opcion = Integer.valueOf(teclado.next());
                teclado.nextLine();
                switch (opcion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        buscarAutorsVivoEnFecha();
                        break;
                    case 5:
                        buscarLibrosPorIdioma();
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }catch (NumberFormatException e) {
                System.out.println("Verifique los datos ingresados");
            }
        }
    }


    private void buscarLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var busqueda = teclado.nextLine();
        var json = consumoApi.obtenerDatosApi(URL_BASE+busqueda.replace(" ", "+"));
        var datosConvertidos = convierteDatos.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroOptional=datosConvertidos.libro().stream().findFirst();

        if (libroOptional.isPresent()) {
            DatosLibro datosLibro = libroOptional.get();
            DatosAutor datosAutor = datosLibro.autor().get(0);

            var nombreLibro = datosLibro.titulo();

            Optional<Libro>libroRepositorio=repositorioLibro.findByTitulo(nombreLibro);
            if (libroRepositorio.isPresent()){
                System.out.println("El libro ya esta guardado en la base de datos");
                return;
            }

            var nombreAutor = datosAutor.nombre();

            Optional<Autor> autorRepositorio = repositorioAutor.findByNombre(nombreAutor);

            if (autorRepositorio.isPresent()) {

                libro = new Libro(datosLibro,autorRepositorio.get());
                repositorioLibro.save(libro);
                System.out.println(libro);

            }else {

                autor=repositorioAutor.save(new Autor(datosAutor));
                libro = new Libro(datosLibro,autor);
                repositorioLibro.save(libro);
                System.out.println(libro);
            }
        }else {

            System.out.println("## El libro no fue encontrado ##");

        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> librosGuardados=repositorioLibro.listarLibrosGuardados();
        librosGuardados.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor>autoresGuardados=repositorioAutor.listarAutoresGuardados();

        for (int i = 0; i < autoresGuardados.size(); i++) {
            List<Libro> libros = repositorioLibro.buscarLibroPorAutor(autoresGuardados.get(i).getId());
            List<String>nombreDELibro= libros.stream().map(Libro::getTitulo).toList();
            autor=autoresGuardados.get(i);
            autor.autorConLibros(nombreDELibro);
        }
    }

    private void buscarAutorsVivoEnFecha() {

        System.out.println("Ingrese el año del que desea buscar autores vivos");
        try {
            var fecha = Integer.parseInt(teclado.next());
            List<Autor> autoresVivosEnFecha = repositorioAutor.autoresVivosEnFecha(fecha);
            if (autoresVivosEnFecha.isEmpty()){
                System.out.println("## No se encontraron autores vivos para la fecha que desea buscar ##");
            }else {
            autoresVivosEnFecha.forEach(System.out::println);
            }
        }catch (NumberFormatException e){
            System.out.println("## Entrada no válida. Por favor, ingrese un número entero ##");
        }
    }

    private void buscarLibrosPorIdioma() {

         String idioma ="";
        int opcionIdioma = -1;

            System.out.println("""
                    *****************************************
                    Seleccione el numero del idioma del libro 
                    *****************************************
                                    1- INGLES
                                    2- ESPAÑOL
                                    3- FRANCES
                                    4- PORTUGUES
                                    5- ITALIANO  
                    ***************************************** 
                    """);
            System.out.println("Ingrese el idioma de los libros que desea listar");
            opcionIdioma = Integer.parseInt(teclado.next());
            switch (opcionIdioma) {
                case 1:
                    idioma = "en";
                    break;
                case 2:
                    idioma = "es";
                    break;
                case 3:
                    idioma = "fr";
                    break;
                case 4:
                    idioma = "pt";
                    break;
                case 5:
                    idioma = "it";
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }


        Idiomas idiomaEnum = Idiomas.fromString(idioma);
        List<Libro>librosPorIdioma=repositorioLibro.buscarLibrosPorIdioma(idiomaEnum);
        if (librosPorIdioma.isEmpty()){
            System.out.println("No se encontraros libros para el idioma seleccionado");
        }else {
            System.out.println("***********  LIBROS EN "+idiomaEnum+"  ***********");
            librosPorIdioma.forEach(System.out::println);
        }

    }

}



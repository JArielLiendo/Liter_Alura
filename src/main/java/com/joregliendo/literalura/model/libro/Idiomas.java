package com.joregliendo.literalura.model.libro;

public enum Idiomas {
    INGLES("en"),
    ESPAÑOL("es"),
    FRANCES("fr"),
    PORTUGUES("pt"),
    ITALIANO("it");

    private String idiomaApi;

    Idiomas (String lenguaje){
        this.idiomaApi=lenguaje;
    }

    public static Idiomas fromString(String text){

        try{
            for (Idiomas idiomas : Idiomas.values()){
                 if (idiomas.idiomaApi.equalsIgnoreCase(text)){
                    return idiomas;
                }

            }
        }catch (IllegalArgumentException e){
            System.out.println("Ningun idioma fue encontrado "+text);
        }
        return null;
        }

    }


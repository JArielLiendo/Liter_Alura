package com.joregliendo.literalura;


import com.joregliendo.literalura.principal.Principal;
import com.joregliendo.literalura.repository.AutorRepository;
import com.joregliendo.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository repositoryAutor;

	@Autowired
	private LibroRepository repositoryLibro;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal=new Principal(repositoryAutor, repositoryLibro);
		principal.ejecutaMenu();
	}

}

package br.com.lopesantonio.Sistema.de.matriculas;

import Main.Main;
import br.com.lopesantonio.dataBase.AlunoRepo;
import br.com.lopesantonio.dataBase.MateriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.lopesantonio.dataBase")
@EntityScan(basePackages = "br.com.lopesantonio.dataBase")
public class SistemaDeMatriculasApplication implements CommandLineRunner {

	@Autowired
	private AlunoRepo alunoRepo;
	@Autowired
	private MateriaRepo materiaRepo;

	public static void main(String[] args) {SpringApplication.run(SistemaDeMatriculasApplication.class, args);}


	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(alunoRepo, materiaRepo);
		main.menu();
	}
}

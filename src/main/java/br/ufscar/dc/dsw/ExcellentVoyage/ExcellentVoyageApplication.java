package br.ufscar.dc.dsw.ExcellentVoyage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.ExcellentVoyage.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.ExcellentVoyage.domain.Usuario;

@SpringBootApplication
public class ExcellentVoyageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcellentVoyageApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
			
			Usuario admin = usuarioDAO.findByEmail("admin@email.com");

			if(admin == null){
				admin = new Usuario();
				admin.setNome("admin");
				admin.setSenha(encoder.encode("admin"));
				admin.setEmail("admin@email.com");
				admin.setTipo("ROLE_admin");
				usuarioDAO.save(admin);
			}
		};
	}
}

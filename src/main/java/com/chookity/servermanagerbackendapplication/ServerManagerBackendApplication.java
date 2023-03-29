package com.chookity.servermanagerbackendapplication;

import com.chookity.servermanagerbackendapplication.enumeration.Status;
import com.chookity.servermanagerbackendapplication.model.Server;
import com.chookity.servermanagerbackendapplication.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagerBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(final ServerRepository serverRepository){
		return args -> {
			serverRepository.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.30", "Fedora Linux", "60.3GB", "Mobile Phone", "http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.42", "MS DOS", "512GB", "Dell Tower", "http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.14", "OS2", "64GB", "HP BladeSystem", "http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));
		};
	}
}

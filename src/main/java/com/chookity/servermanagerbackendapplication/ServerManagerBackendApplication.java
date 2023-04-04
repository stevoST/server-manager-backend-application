package com.chookity.servermanagerbackendapplication;

import com.chookity.servermanagerbackendapplication.enumeration.Status;
import com.chookity.servermanagerbackendapplication.model.Server;
import com.chookity.servermanagerbackendapplication.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

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

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}

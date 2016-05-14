package daw.videoclub;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
public class VideoclubApp extends WebMvcConfigurerAdapter {
	
	public static void main(String[] args) {
		SpringApplication.run(VideoclubApp.class, args);
	}
}
package nl.maastrichtuniversity.ids.dspacecliapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DspaceCliApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DspaceCliApiApplication.class, args);
	}

}

package com.clarivate.testrestapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(ScoreRepository repository) {
		return args -> {
			log.info(String.format("Preloading %s", repository.save(new Score(0, 1, 1548))));
			log.info(String.format("Preloading %s", repository.save(new Score(0, 1, 1254))));
			log.info(String.format("Preloading %s", repository.save(new Score(0, 1, 470))));
			log.info(String.format("Preloading %s", repository.save(new Score(0, 2, 1800))));
			log.info(String.format("Preloading %s", repository.save(new Score(0, 2, 1952))));
			log.info(String.format("Preloading %s", repository.save(new Score(0, 2, 1470))));
			log.info(String.format("Preloading %s", repository.save(new Score(0, 3, 2470))));
			log.info(String.format("Preloading %s", repository.save(new Score(0, 3, 470))));
			log.info(String.format("Preloading %s", repository.save(new Score(0, 3, 1470))));

			log.info(String.format("Preloading %s", repository.save(new Score(1, 1, 1470))));
			log.info(String.format("Preloading %s", repository.save(new Score(1, 1, 1254))));
			log.info(String.format("Preloading %s", repository.save(new Score(1, 1, 470))));
			log.info(String.format("Preloading %s", repository.save(new Score(1, 2, 1470))));
			log.info(String.format("Preloading %s", repository.save(new Score(1, 2, 1952))));
			log.info(String.format("Preloading %s", repository.save(new Score(1, 2, 470))));
			log.info(String.format("Preloading %s", repository.save(new Score(1, 3, 1548))));
			log.info(String.format("Preloading %s", repository.save(new Score(1, 3, 470))));
			log.info(String.format("Preloading %s", repository.save(new Score(1, 3, 1800))));
		};
	}
}

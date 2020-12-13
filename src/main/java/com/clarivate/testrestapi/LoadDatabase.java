package com.clarivate.testrestapi;

import com.clarivate.testrestapi.score.Score;
import com.clarivate.testrestapi.score.ScoreRepository;
import com.clarivate.testrestapi.user.User;
import com.clarivate.testrestapi.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, ScoreRepository scoreRepository) {
		return args -> {
			User user1 = new User("mestevez", "secret");
			log.info(String.format("Preloading %s", userRepository.save(user1)));
			
			User user2 = new User("testuser", "secretword");
			log.info(String.format("Preloading %s", userRepository.save(user2)));

			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 1, 1548))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 1, 1254))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 1, 470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 2, 1800))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 2, 1952))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 2, 1470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 3, 2470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 3, 470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 3, 1470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 3, 8645))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 3, 16))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user1.getUserId(), 3, 1567))));

			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user2.getUserId(), 1, 1470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user2.getUserId(), 1, 1254))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user2.getUserId(), 1, 470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user2.getUserId(), 2, 1470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user2.getUserId(), 2, 1952))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user2.getUserId(), 2, 470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user2.getUserId(), 3, 1548))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user2.getUserId(), 3, 470))));
			log.info(String.format("Preloading %s", scoreRepository.save(new Score(user2.getUserId(), 3, 1800))));
		};
	}
}

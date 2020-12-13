package com.clarivate.testrestapi.score;

import com.clarivate.testrestapi.user.User;
import com.clarivate.testrestapi.user.UserBadCredentialsException;
import com.clarivate.testrestapi.user.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
class ScoreController {

	private final UserRepository userRepository;
	private final ScoreRepository repository;

	public ScoreController(UserRepository userRepository, ScoreRepository repository) {
		this.userRepository = userRepository;
		this.repository = repository;
	}

	/**
	 * Retrieves the highest scores for an specific level per user.
	 */
	@GetMapping("/level/{level}/score")
	ResponseEntity getScores(
			Authentication auth,
			@RequestParam(name = "filter", required = false) String filter,
			@PathVariable("level") int level
	) {
		String username = Objects.requireNonNull(auth.getCredentials()).toString();
		User user = Objects.requireNonNull(userRepository.findByUsername(username))
				.orElseThrow(() -> new UserBadCredentialsException());

		List<Score> scoreList = filter != null && filter.equals("highestscore")
				? repository.findTop5ByUserIdAndLevelOrderByValueDesc(user.getUserId(), level)
				: repository.findByUserIdAndLevel(user.getUserId(), level);

		return ResponseEntity
				.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(scoreList);
	}

	/**
	 * Adds a new score for the current user in session and the received level.
	 * <p>
	 * This endpoint can be called several times per user and level and does not return anything.
	 * Only requests with valid session keys should be processed.
	 *
	 * @return
	 */
	@PutMapping("/level/{level}/score/{value}")
	ResponseEntity addScore(
			Authentication auth,
			@PathVariable("level") int level,
			@PathVariable("value") int value
	) {
		String username = Objects.requireNonNull(auth.getCredentials()).toString();
		User user = Objects.requireNonNull(userRepository.findByUsername(username))
				.orElseThrow(() -> new UserBadCredentialsException());

		repository.save(new Score(user.getUserId(), level, value));

		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
}

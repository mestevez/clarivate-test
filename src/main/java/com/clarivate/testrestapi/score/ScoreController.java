package com.clarivate.testrestapi.score;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ScoreController {

	private final ScoreRepository repository;

	public ScoreController(ScoreRepository repository) {
		this.repository = repository;
	}

	/**
	 * Retrieves the highest scores for an specific level per user.
	 */
	@GetMapping("/level/{level}/score")
	ResponseEntity getScores(
		@PathVariable("level") int level
	) {
		// TODO: Get userId from session
		int userId = 0;

		return ResponseEntity
				.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(repository.findHighestByUserIdAndLevelOrderByValueDesc(userId, level));
	}

	/**
	 * Adds a new score for the current user in session and the received level.
	 *
	 * This endpoint can be called several times per user and level and does not return anything.
	 * Only requests with valid session keys should be processed.
	 *
	 * @return
	 */
	@PutMapping("/level/{level}/score/{value}")
	ResponseEntity addScore(
		@PathVariable("level") int level,
		@PathVariable("value") int value
	) {
		// TODO: Get userId from session
		int userId = 0;

		repository.save(new Score(userId, level, value));

		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
}

package com.clarivate.testrestapi;

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

	/**
	 * Retrieves the highest scores for an specific level per user.
	 */
	@GetMapping("/level/{levelId}/score")
	ResponseEntity getScores(
		@PathVariable("levelId") int levelId
	) {
		// TODO: Implement service code

		return ResponseEntity
				.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body("[{ \"userid\": 0, \"score\": 500 }, { \"userid\": 0, \"score\": 488 }, { \"userid\": 0, \"score\": 470 }]");
	}

	/**
	 * Adds a new score for the current user in session and the received level.
	 *
	 * This endpoint can be called several times per user and level and does not return anything.
	 * Only requests with valid session keys should be processed.
	 *
	 * @return
	 */
	@PutMapping("/level/{levelId}/score/{value}")
	ResponseEntity addScore(
		@PathVariable("levelId") int levelId,
		@PathVariable("value") int value
	) {
		// TODO: Implement service code

		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
}

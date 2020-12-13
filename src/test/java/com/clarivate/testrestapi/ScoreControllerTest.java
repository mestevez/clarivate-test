package com.clarivate.testrestapi;

import com.clarivate.testrestapi.security.token.jwt.AuthTokenGeneratorJWT;
import com.clarivate.testrestapi.security.token.jwt.JWTProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(ScoreController.class)
@ContextConfiguration(classes = ScoreRepository.class)
class ScoreControllerTest {

	@Autowired
	private MockMvc mvc;

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void addScore() throws Exception {
		JWTProperties jwtProperties = new JWTProperties();

		MvcResult mvcResult = mvc.perform(
			MockMvcRequestBuilders
					.put("/level/{level}/score/{value}", 3, 500)
					.header(jwtProperties.getHeader(), new AuthTokenGeneratorJWT("mestevez", jwtProperties).getToken())
		).andReturn();

		assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void addScoreNotLogged() throws Exception {
		MvcResult mvcResult = mvc.perform(
			MockMvcRequestBuilders.put("/level/{level}/score/{value}", 3, 500)
		).andReturn();

		assertEquals(HttpStatus.FORBIDDEN.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void getScores() throws Exception {
		JWTProperties jwtProperties = new JWTProperties();

		MvcResult mvcResult = mvc.perform(
			MockMvcRequestBuilders
					.get("/level/{level}/score", 3)
					.header(jwtProperties.getHeader(), new AuthTokenGeneratorJWT("mestevez", jwtProperties).getToken())
		).andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");

		List<Object> mvcResultScoreList = JsonParserFactory.getJsonParser().parseList(mvcResult.getResponse().getContentAsString());

		int[] expectedScores = new int[]{ 2470 , 1470, 470};

		assertEquals(expectedScores.length, mvcResultScoreList.size());
		for (int expectedScoreIndex = 0; expectedScoreIndex < expectedScores.length; expectedScoreIndex++) {
			int expectedScore = expectedScores[expectedScoreIndex];
			Map<String, Object> mvcResultScoreMap = (Map<String, Object>) mvcResultScoreList.get(expectedScoreIndex);

			assertEquals(0, mvcResultScoreMap.get("userid"), String.format("Unexpected userid at index=%s", expectedScoreIndex));
			assertEquals(expectedScore, mvcResultScoreMap.get("score"), String.format("Unexpected score at index=%s", expectedScoreIndex));
		}
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	void getScoresNotLogged() throws Exception {
		MvcResult mvcResult = mvc.perform(
			MockMvcRequestBuilders.get("/level/{level}/score", 3)
		).andReturn();

		assertEquals(HttpStatus.FORBIDDEN.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");
	}
}

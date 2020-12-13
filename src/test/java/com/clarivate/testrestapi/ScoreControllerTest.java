package com.clarivate.testrestapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class ScoreControllerTest {

	@Autowired
	private MockMvc mvc;

	private String authToken;

	@BeforeEach
	void beforeEachScoreControllerTest() throws Exception {
		MvcResult mvcResultLogin = mvc.perform(
				MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"username\": \"mestevez\", \"password\": \"secret\" }")
		).andReturn();

		assertEquals(HttpStatus.ACCEPTED.value(), mvcResultLogin.getResponse().getStatus(), "Unexpected response code");
		authToken = mvcResultLogin.getResponse().getContentAsString();
		assertNotNull(authToken);
	}

	/**
	 * @throws Exception
	 */
	@Test
	void addScore() throws Exception {
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders
						.put("/level/{level}/score/{value}", 1, 500)
						.header(HttpHeaders.AUTHORIZATION, authToken)
		).andReturn();

		assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");
	}

	/**
	 * @throws Exception
	 */
	@Test
	void addScoreNotLogged() throws Exception {
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put("/level/{level}/score/{value}", 1, 500)
		).andReturn();

		assertEquals(HttpStatus.FORBIDDEN.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");
	}

	/**
	 * @throws Exception
	 */
	@Test
	void getScores() throws Exception {
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders
						.get("/level/{level}/score", 3)
						.header(HttpHeaders.AUTHORIZATION, authToken)
		).andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");

		List<Object> mvcResultScoreList = JsonParserFactory.getJsonParser().parseList(mvcResult.getResponse().getContentAsString());

		int[] expectedScores = new int[]{2470, 1470, 470};

		assertEquals(expectedScores.length, mvcResultScoreList.size(), String.format("Unexpected list size: %s", mvcResultScoreList));
		for (int expectedScoreIndex = 0; expectedScoreIndex < expectedScores.length; expectedScoreIndex++) {
			int expectedScore = expectedScores[expectedScoreIndex];
			Map<String, Object> mvcResultScoreMap = (Map<String, Object>) mvcResultScoreList.get(expectedScoreIndex);

			assertEquals(0, mvcResultScoreMap.get("userId"), String.format("Unexpected userId at index=%s", expectedScoreIndex));
			assertEquals(expectedScore, mvcResultScoreMap.get("value"), String.format("Unexpected value at index=%s", expectedScoreIndex));
		}
	}

	/**
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

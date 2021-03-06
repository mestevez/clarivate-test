package com.clarivate.testrestapi.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mvc;

	@Test
	void loginSuccess() throws Exception {
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"username\": \"mestevez\", \"password\": \"secret\" }")
		).andReturn();

		assertEquals(HttpStatus.ACCEPTED.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");
		assertNotNull(mvcResult.getResponse().getContentAsString());
	}

	@Test
	void invalidUser() throws Exception {
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"username\": \"fake\", \"password\": \"fake\" }")
		).andReturn();

		assertEquals(HttpStatus.FORBIDDEN.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");
	}

	@Test
	void invalidPassword() throws Exception {
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"username\": \"mestevez\", \"password\": \"fake\" }")
		).andReturn();

		assertEquals(HttpStatus.FORBIDDEN.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");
	}

	@Test
	void loginMissingParams() throws Exception {
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
		).andReturn();

		assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus(), "Unexpected response code");
	}
}

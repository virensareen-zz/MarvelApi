package com.yapily.marvel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MarvelEndPointTests {

	@Autowired
	private MockMvc mockMvc;
	private String endpoint;

	@BeforeAll
	public void Setup(){

	}

	@Test
	public void testGetCharactersOk() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/characters").accept(MediaType.APPLICATION_JSON)).andDo(print())
		.andExpect(status().isOk())
				.andExpect(jsonPath("$.code", is(200)))
				.andExpect(jsonPath("$.status", is("Ok")))
				.andExpect(jsonPath("$.data.offset", is(0)))
				.andExpect(jsonPath("$.data.total", is(1485)))
				.andExpect(jsonPath("$.data.limit", is(10)))
				.andExpect(jsonPath("$.data.results[0].id", is(1011334)))
				.andExpect(jsonPath("$.data.results[0].name", is("3-D Man")))
				.andExpect(jsonPath("$.data.results[1].id", is(1017100)))
				.andExpect(jsonPath("$.data.results[1].name", is("A-Bomb (HAS)")))
				.andExpect(jsonPath("$.data.results[2].id", is(1009144)))
				.andExpect(jsonPath("$.data.results[2].name", is("A.I.M.")))
				.andExpect(jsonPath("$.data.results[3].id", is(1010699)))
				.andExpect(jsonPath("$.data.results[3].name", is("Aaron Stack")))
				.andExpect(jsonPath("$.data.results[4].id", is(1009146)))
				.andExpect(jsonPath("$.data.results[4].name", is("Abomination (Emil Blonsky)")))
				.andExpect(jsonPath("$.data.results[5].id", is(1016823)))
				.andExpect(jsonPath("$.data.results[5].name", is("Abomination (Ultimate)")))
				.andExpect(jsonPath("$.data.results[6].id", is(1009148)))
				.andExpect(jsonPath("$.data.results[6].name", is("Absorbing Man")))
				.andExpect(jsonPath("$.data.results[7].id", is(1009149)))
				.andExpect(jsonPath("$.data.results[7].name", is("Abyss")))
				.andExpect(jsonPath("$.data.results[8].id", is(1010903)))
				.andExpect(jsonPath("$.data.results[8].name", is("Abyss (Age of Apocalypse)")))
				.andExpect(jsonPath("$.data.results[9].id", is(1011266)))
				.andExpect(jsonPath("$.data.results[9].name", is("Adam Destine")));
	}

}

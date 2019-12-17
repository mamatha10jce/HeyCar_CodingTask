package com.mamatha.codingtask.heycarapplication.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mamatha.codingtask.heycarapplication.model.CarDetails;
import com.mamatha.codingtask.heycarapplication.repository.CarDetailsRepository;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CarDetailsRepository repository;

	@Before
	public void setup() {
		repository.save(new CarDetails("make", "model", 9999, "color", new BigDecimal(1000), 2019));
		repository.save(new CarDetails("VW", "Polo", 180, "red", new BigDecimal(2000), 2015));
		repository.save(new CarDetails("audi", "Vento", 181, "Blue", new BigDecimal(2001), 2016));
		repository.save(new CarDetails("Tata", "Bolt", 182, "white", new BigDecimal(201502), 2014));
	}

	@After
	public void tearDown() {
		repository.deleteAll();
	}

	@Test
	public void testSearchWithNoParam() throws Exception {

		mockMvc.perform(get("/search")).andExpect(status().isOk()).andExpect(jsonPath("content.[0].make").value("VW"))
				.andExpect(jsonPath("content.[0].model").value("polo"))
				.andExpect(jsonPath("content.[0].power").value("180"))
				.andExpect(jsonPath("content.[0].color").value("red"))
				.andExpect(jsonPath("content.[0].price").value("2000.0"))
				.andExpect(jsonPath("content.[0].year").value("2015"));
	}

	@Test
	public void testSearchByMake() throws Exception {
		mockMvc.perform(get("/search?make=VW")).andExpect(status().isOk())
				.andExpect(jsonPath("content.[0].make").value("VW"))
				.andExpect(jsonPath("content.[0].model").value("polo"))
				.andExpect(jsonPath("content.[0].power").value("180"))
				.andExpect(jsonPath("content.[0].color").value("red"))
				.andExpect(jsonPath("content.[0].price").value("2000.0"))
				.andExpect(jsonPath("content.[0].year").value("2015"));
	}

	@Test
	public void testSearchByModel() throws Exception {
		mockMvc.perform(get("/search?model=polo")).andExpect(status().isOk())
				.andExpect(jsonPath("content.[0].make").value("VW"))
				.andExpect(jsonPath("content.[0].model").value("polo"))
				.andExpect(jsonPath("content.[0].power").value("180"))
				.andExpect(jsonPath("content.[0].color").value("red"))
				.andExpect(jsonPath("content.[0].price").value("2000.0"))
				.andExpect(jsonPath("content.[0].year").value("2015"));
	}

	@Test
	public void testSearchByYear() throws Exception {

		mockMvc.perform(get("/search?year=2015")).andExpect(status().isOk())
				.andExpect(jsonPath("content.[0].make").value("VW"))
				.andExpect(jsonPath("content.[0].model").value("polo"))
				.andExpect(jsonPath("content.[0].power").value("180"))
				.andExpect(jsonPath("content.[0].color").value("red"))
				.andExpect(jsonPath("content.[0].price").value("2000.0"))
				.andExpect(jsonPath("content.[0].year").value("2015"));
	}

	@Test
	public void testSearchByColor() throws Exception {
		mockMvc.perform(get("/search?color=red")).andExpect(status().isOk())
				.andExpect(jsonPath("content.[0].make").value("VW"))
				.andExpect(jsonPath("content.[0].model").value("polo"))
				.andExpect(jsonPath("content.[0].power").value("180"))
				.andExpect(jsonPath("content.[0].color").value("red"))
				.andExpect(jsonPath("content.[0].price").value("2000.0"))
				.andExpect(jsonPath("content.[0].year").value("2015"));
	}

}

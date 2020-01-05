package com.mindtree.friendlyally.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.friendlyally.dto.DoctorDetailsDto;
import com.mindtree.friendlyally.dto.GroceryDto;
import com.mindtree.friendlyally.service.GroceryService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(GroceryController.class)
public class GroceryControllerTest {

	@InjectMocks
	private GroceryController groceryController;

	@Mock
	private GroceryService groceryService;

	@Autowired
	private MockMvc mockMvc;
	
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(groceryController).build();
	}
	
	@Test
	public void getAllGroceriesTest() throws Exception {

		List<GroceryDto> groceryList = new ArrayList<GroceryDto>();

		for (int i = 0; i < 3; i++) {
			GroceryDto grocery = new GroceryDto();
			grocery.setGroceryId(i);
			grocery.setGroceryName("Apple");
			grocery.setGroceryDescription("Apple is sweet");
			grocery.setGroceryPrice(123);
			grocery.setActive(true);
		
			groceryList.add(grocery);
		}

		Mockito.when(groceryService.getGroceriesFromDb()).thenReturn(groceryList);
		mockMvc.perform(MockMvcRequestBuilders.get("/groceries/grocery").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void updateStatus() throws Exception {
		int groceryId=1;
		GroceryDto grocery = new GroceryDto();
		grocery.setGroceryId(1);
		grocery.setActive(true);
		grocery.setGroceryName("Apple");
		grocery.setGroceryDescription("Apple is sweet"); 
		grocery.setGroceryPrice(123);
		grocery.setImageUrl("I");
		String gro="grocery  Added";
		Mockito.when(groceryService.updateGrocery(Mockito.refEq(grocery), Mockito.eq(groceryId))).thenReturn(gro);
		mockMvc.perform(MockMvcRequestBuilders.put("/groceries/update-status/1").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(grocery)).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void addNewGrocery() throws Exception {
		GroceryDto grocery = new GroceryDto();
		grocery.setGroceryId(1);
		grocery.setActive(true);
		grocery.setGroceryName("Apple");
		grocery.setGroceryDescription("Apple is sweet");
		grocery.setGroceryPrice(123);
		grocery.setImageUrl("I");
		Mockito.when(groceryService.addNewGrocery(Mockito.refEq(grocery))).thenReturn(grocery);
		mockMvc.perform(MockMvcRequestBuilders.post("/groceries/add-new").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(grocery)).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void editGrocery() throws Exception {
		GroceryDto grocery = new GroceryDto();
		grocery.setGroceryId(1);
		grocery.setActive(true);
		grocery.setGroceryName("Apple");
		grocery.setGroceryDescription("Apple is sweet");
		grocery.setGroceryPrice(123);
		grocery.setImageUrl("I");
		Mockito.when(groceryService.editGrocery((Mockito.refEq(grocery)))).thenReturn(grocery);
		mockMvc.perform(MockMvcRequestBuilders.put("/groceries/grocery-edit").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(grocery)).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	


}

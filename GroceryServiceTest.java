package com.mindtree.friendlyally.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.friendlyally.dto.GroceryDto;
import com.mindtree.friendlyally.entity.Grocery;
import com.mindtree.friendlyally.repository.GroceryRepository;
import com.mindtree.friendlyally.repository.UserRepository;
import com.mindtree.friendlyally.service.serviceimpl.GroceryServiceImpl;
import com.mindtree.friendlyally.service.serviceimpl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class GroceryServiceTest {

	@TestConfiguration
	static class TestConfig {
		@Bean
		public GroceryService groceryService() {
			return new GroceryServiceImpl();
		}
	}
	
	@Autowired
	private GroceryService groceryService;

	@Autowired
	private GroceryServiceImpl grocer;

	@MockBean
	private GroceryRepository groceryRepository;

	@Before
	public void setup() {

	}
	
	
	@Test
	public void getAllGroceryTest()
	{
		List<GroceryDto> groceryDtoList = new ArrayList<GroceryDto>();
		List<Grocery> groceryList = new ArrayList<Grocery>();
		Grocery groc= new Grocery();
		groc.setGroceryId(1);
		groc.setGroceryName("apple");
		groc.setGroceryPrice(1234);
		groc.setImageUrl("i");
		groc.setActive(true);
		groceryList.add(groc);
		
		GroceryDto grocDt= new GroceryDto();
		grocDt.setGroceryId(1);
		grocDt.setGroceryName("apple");
		grocDt.setGroceryPrice(1234);
		
		grocDt.setImageUrl("i");
		grocDt.setActive(true);
		groceryDtoList.add(grocDt);
		
		Mockito.when(groceryRepository.findAll()).thenReturn(groceryList);
		assertEquals(groceryDtoList, groceryService.getGroceriesFromDb());
		
	}

	@Test
	public void addGrocery()
	{
		Grocery groc= new Grocery();
		groc.setGroceryId(1);
		groc.setGroceryName("apple");
		groc.setGroceryPrice(1234);
		groc.setImageUrl("i");
		groc.setActive(true);
	
		
		GroceryDto grocDt= new GroceryDto();
		grocDt.setGroceryId(1);
		grocDt.setGroceryName("apple");
		grocDt.setGroceryPrice(1234);
		
		grocDt.setImageUrl("i");
		grocDt.setActive(true);
		
		
		//Mockito.when(groceryRepository.findAll()).thenReturn(groceryList);
		assertEquals(grocDt, groceryService.addNewGrocery(grocDt));
		
	}
	@Test
	public void updateGrocery()
	{
		Grocery groc= new Grocery();
		groc.setGroceryId(1);
		groc.setGroceryName("apple");
		groc.setGroceryPrice(1234);
		groc.setImageUrl("i");
		groc.setActive(true);
		
		GroceryDto grocDt= new GroceryDto();
		grocDt.setGroceryId(1);
		grocDt.setGroceryName("apple");
		grocDt.setGroceryPrice(1234);
		
		grocDt.setImageUrl("i");
		grocDt.setActive(true);
		
		String grocc="Grocery updated";
		Mockito.when(groceryRepository.findById(1)).thenReturn(Optional.of(groc));
		Mockito.when(groceryRepository.saveAndFlush(groc)).thenReturn(groc);
		assertEquals(grocc, groceryService.updateGrocery(grocDt,1));
		
	}
	
	@Test
	public void editGrocery()
	{
		Grocery groc= new Grocery();
		groc.setGroceryId(1);
		groc.setGroceryName("apple");
		groc.setGroceryPrice(1234);
		groc.setImageUrl("i");
		groc.setActive(true);
		
		GroceryDto grocDt= new GroceryDto();
		grocDt.setGroceryId(1);
		grocDt.setGroceryName("apple");
		grocDt.setGroceryPrice(1234);
		
		grocDt.setImageUrl("i");
		grocDt.setActive(true);
		
		
		Mockito.when(groceryRepository.findById(1)).thenReturn(Optional.of(groc));
		Mockito.when(groceryRepository.saveAndFlush(groc)).thenReturn(groc);
		assertEquals(grocDt, groceryService.editGrocery(grocDt));
		
	}

	
	
}

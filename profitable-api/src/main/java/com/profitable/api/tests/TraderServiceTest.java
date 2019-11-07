package com.profitable.api.tests;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.profitable.api.ProfitableApp;
import com.profitable.api.repositories.TraderRepository;
import com.profitable.api.service.TraderService;
import com.profitable.model.Trader;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		ProfitableApp.class
})
public class TraderServiceTest {
	
	@Autowired
	private TraderService service;
	
	@MockBean
	private TraderRepository repository;
	
	@Test
	public void mustCreate() {
		Trader trader = Trader
				.builder()
				.firstName("Renan")
				.lastName("Rodrigues")
				.build();
		given(repository.save(trader)).willReturn(trader);
		assertNotNull(service.create(trader));
	}
	

	
}

package com.profitable.api.resources;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profitable.api.service.TraderService;
import com.profitable.model.EntityType;
import com.profitable.model.Trader;

@RestController
@RequestMapping("traders")
public class TraderResource implements GenericController<Trader> {
	
	@Autowired
	private TraderService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountResource.class);
	
	@Override
	@GetMapping
	public ResponseEntity<List<Trader>> list() {
		return ResponseEntity.ok(service.all());
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Trader> save(@RequestBody Trader trader) {
		LOGGER.info("Creating user " + trader);
		Trader createdTrader = service.create(trader);
		createdTrader.add(addSelfLink(this, trader));
		return ResponseEntity.ok(trader);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<Trader> searchById(@PathVariable("id") Long id) {
		Optional<Trader> optTrader = service.query(id);
		if (optTrader.isPresent()) {
			Trader trader = optTrader.get();
			trader.add(addListLink(this, EntityType.TRADER));
			return ResponseEntity.ok(trader);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok(true);
	}
	
}

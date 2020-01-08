package com.northrow.freezer.catalog.api.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.northrow.freezer.catalog.api.exception.ResourceNotFoundException;
import com.northrow.freezer.catalog.api.model.Freezer;
import com.northrow.freezer.catalog.api.repo.FreezerRepo;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
@RequestMapping("/api/v1")
public class FreezerController {
	
	@Autowired
	FreezerRepo freezerRepo;
    
	/*  Method to provide ID and get details of the food  */
	
	@GetMapping("/freezer/{id}")
	public ResponseEntity<Freezer> getItemsById(@PathVariable(value = "id" ) Integer freezerId) throws ResourceNotFoundException{
		Freezer freezer = freezerRepo.findById(freezerId)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found "+freezerId));
		return ResponseEntity.ok().body(freezer);
		
	}

	/*  Method to update an item */
	
	@PutMapping("/freezer/{id}")
	public ResponseEntity<Freezer> updateFreezerItem(@PathVariable(value = "id") Integer freezerId,@Valid @RequestBody Freezer freezerDetails) throws ResourceNotFoundException{
		Freezer freezer = freezerRepo.findById(freezerId)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found "+freezerId));
		freezer.setFoodName(freezerDetails.getFoodName());
		freezer.setFoodType(freezerDetails.getFoodType());
		freezer.setQuantity(freezerDetails.getQuantity());
		freezer.setDateAdded(freezerDetails.getDateAdded());
		
		final Freezer updatedFreezerItem = freezerRepo.save(freezer);
		
		return ResponseEntity.ok(updatedFreezerItem);
		
	}
	
	/*  Method to add some food to the freezer  */
	
	@PostMapping("/freezer")
	public Freezer saveFreezerItem(@Valid @RequestBody Freezer freezer) {
		return freezerRepo.save(freezer);
		
	}
	
	/*  Method to search food by name,type or date added  */
	
	 @GetMapping("/freezer")
	    public ResponseEntity<List<Freezer>> searchForFood(@SearchSpec Specification<Freezer> specs) {
	        return new ResponseEntity<>(freezerRepo.findAll(Specification.where(specs)), HttpStatus.OK);
	    }
}
	

	
	


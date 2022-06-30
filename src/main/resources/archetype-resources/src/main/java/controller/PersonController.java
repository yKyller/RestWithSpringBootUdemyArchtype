#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ${package}.data.vo.v1.PersonVO;
import ${package}.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Person Endpoint", description = "Endpoint para Persons", tags = {"PersonEndpoint"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController { 
	
	@Autowired
	private PersonServices services;
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;
	
	@ApiOperation(value = "Find all people recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="size", defaultValue = "12") int size,
			@RequestParam(value="sort", defaultValue = "firstName,asc") String sort) {
		
		sort = sort.split(",").length < 2 ? "firstName,asc" : sort;
		
		var sortColumn = sort.split(",")[0];
		
		var sortDirection = "desc".equalsIgnoreCase(sort.split(",")[1]) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortColumn));
		
		Page<PersonVO> persons = services.findAll(pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(persons);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find all people recorded with token name")
	@GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="size", defaultValue = "12") int size,
			@RequestParam(value="sort", defaultValue = "firstName,asc") String sort) {
		
		var sortDirection = "desc".equalsIgnoreCase(sort.split(",")[1]) ? Direction.DESC : Direction.ASC;
		
		var sortColumn = sort.split(",")[0];
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortColumn));
		
		Page<PersonVO> persons = services.findPersonByName(firstName, pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(persons);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a unique people recorded")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Save one record of people")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = services.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Update people recorded")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = services.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Disable a specific person by your ID")
	@PatchMapping(value = "/disable/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO disablePerson(@PathVariable("id") Long id) {
		PersonVO personVO = services.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Enable a specific person by your ID")
	@PatchMapping(value = "/enable/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO enablePerson(@PathVariable("id") Long id) {
		PersonVO personVO = services.enablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Delete people recorded")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}

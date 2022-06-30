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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ${package}.data.vo.v1.BookVO;
import ${package}.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Endpoint", description = "Endpoint para Books", tags = {"BookEndpoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	BookServices services;
	
	@Autowired
	private PagedResourcesAssembler<BookVO> assembler;
	
	@ApiOperation(value = "Find all book recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="size", defaultValue = "12") int size,
			@RequestParam(value="sort", defaultValue = "title,asc") String sort) {
		
		sort = sort.split(",").length < 2 ? "title,asc" : sort;
		
		var sortColumn = sort.split(",")[0];
		
		var sortDirection = "desc".equalsIgnoreCase(sort.split(",")[1]) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortColumn));
		
		Page<BookVO> books = services.findAll(pageable);
		books.stream()
				.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(books);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find all book recorded with token title")
	@GetMapping(value = "/findBookByTitle/{title}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findBookByTitle(
			@PathVariable("title") String title,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="size", defaultValue = "12") int size,
			@RequestParam(value="sort", defaultValue = "title,asc") String sort) {
		
		sort = sort.split(",").length < 2 ? "title,asc" : sort;
		
		var sortColumn = sort.split(",")[0];
		
		var sortDirection = "desc".equalsIgnoreCase(sort.split(",")[1]) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortColumn));
		
		Page<BookVO> books = services.findBookByTitle(title, pageable);
		books.stream()
				.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(books);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a unique book recorded")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVO = services.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Save one record of book")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO book) {
		BookVO bookVO = services.create(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Update book recorded")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO book) {
		BookVO bookVO = services.update(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Delete book recorded")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}

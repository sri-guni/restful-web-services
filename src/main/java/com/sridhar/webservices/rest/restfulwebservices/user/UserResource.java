package com.sridhar.webservices.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserResource {
	private UserDaoService service;

	@Autowired
	public UserResource(UserDaoService service) {
		this.service = service;
	}

	@GetMapping()
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public EntityModel<User> findUserById(@PathVariable int id) {
		User user = service.findById(id);
		if (user == null)
			throw new UserNotFoundException("[id:" + id + "]");
		
		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}

	@PostMapping()
	public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		User deletedUser = service.deleteUser(id);
		if (deletedUser == null) {
			throw new UserNotFoundException("[id:" + id + "]");
		}

		return ResponseEntity.ok(deletedUser);
	}

}

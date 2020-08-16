package com.sridhar.webservices.rest.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 versioningWithHeadersV1() {
		return new PersonV1("Jones");
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 versioningWithHeadersV2() {
		return new PersonV2(new Name("Indiana", "Jones"));
	}

	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 versioningWithProducesV1() {
		return new PersonV1("Jones");
	}
	
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 versioningWithProducesV2() {
		return new PersonV2(new Name("Indiana", "Jones"));
	}

}

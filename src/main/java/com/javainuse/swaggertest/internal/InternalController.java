package com.javainuse.swaggertest.internal;

import com.javainuse.swaggertest.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "another-endpoint",
		description = "Test Another Custom Description",
		value = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Param Defs",
		consumes = "application/json", produces = "application/json")
@RequestMapping(value = "/internal-apis/", consumes = "application/json", produces = "application/json" )
@RestController()
public class InternalController extends BaseController {

	@RequestMapping(method = RequestMethod.GET, value = "hello")
	public String sayHello() {
		return "Swagger Hello World";
	}
}

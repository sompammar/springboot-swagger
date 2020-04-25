package com.javainuse.swaggertest.internal;

import com.javainuse.swaggertest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;


@Api(tags = "another-endpoint",
		description = "Test Another Custom Description",
		value = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Param Defs",
		consumes = "application/json", produces = "application/json")
@RequestMapping(value = "/internal-apis/", consumes = "application/json", produces = "application/json" )
@RestController()
public class InternalController extends BaseController {

	@RequestMapping(method = RequestMethod.GET, value = "hello")
	public String sayHello(@ApiParam(value = "type", allowableValues = "Morning,Evening", defaultValue = "Morning")
						   @RequestParam("type") @NotBlank(message = "RequestParam argument type can not be empty") String type) {
		System.out.println(type);
		return "Swagger Hello World !!! Good " + type + " !!!";
	}


}

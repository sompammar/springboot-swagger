package com.javainuse.swaggertest.external;

import com.javainuse.swaggertest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "hello-endpoint",
		description = "Test Hello Custom Description",
		value = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Param Defs",
		consumes = "application/json", produces = "application/json")
@RestController
public class HelloController extends BaseController {

	@RequestMapping(method = RequestMethod.GET, value = "hello")
	public String sayHello() {
		return "Swagger Hello World";
	}
}

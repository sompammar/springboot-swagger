package com.javainuse.swaggertest;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Param Defs",
		consumes = "application/json", produces = "application/json")
@RequestMapping(value = {"/api/javainuse/{" + SwaggerConfig.SOURCE_TYPE + "}/test2"},
		consumes = "application/json", produces = "application/json" )
@RestController
public class BaseController {


}

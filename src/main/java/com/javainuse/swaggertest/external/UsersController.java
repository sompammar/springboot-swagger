package com.javainuse.swaggertest.external;

import com.javainuse.swaggertest.BaseController;
import com.javainuse.swaggertest.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Api(tags = "users-endpoint",
		description = "Test Users",
		consumes = "application/json", produces = "application/json")
@RestController()
@RequestMapping(path = "/users")
public class UsersController extends BaseController {
	List<User> users = new ArrayList<>();

	@RequestMapping(method = RequestMethod.POST)
//	@ApiImplicitParams({
//			@ApiImplicitParam(
//					name = "user",
//					dataType = "User",
//					value = "{'som'：{'type': 'User'}}",
//					examples = @io.swagger.annotations.Example(
//							value = {
//									@ExampleProperty(value = "{'firstName'：{'type': 'String'}}", mediaType = "application/json")
//							}))
//	})
//	@ApiParam(name = "user", required =  true, examples = @Example(
//	        value = {
//	             @ExampleProperty(value = "firstName"),
//	             @ExampleProperty(value = "lastName")
//            }
//    ))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "modelId", value = "this is modelId", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "makeId", value = "this is makeId", required = true, dataType = "string", paramType = "query")
	})
	public User createUser(@ApiParam(name = "user", examples = @Example(
			value = {
					@ExampleProperty( value = "{ 'firstName' : {'type' : 'String'}}", mediaType = "application/json")
			}
	)) @RequestBody User user, HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			System.out.println(name + " " + request.getHeader(name));
		}
		user.setId(UUID.randomUUID().toString());
		users.add(user);
		return user;
	}




	@RequestMapping(method = RequestMethod.PUT)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "map", value = "this is modelId", required = true, dataType = "Map", paramType = "body",
					examples = @Example(
							value = {
									@ExampleProperty(value = "{ firstName: joe, lastName: carrie}", mediaType = "application/json")
							}
					)),
	})
	public User saveMap(

			 @RequestBody Map<String, String> map) {

		return null;
	}



	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		return users;
	}


}

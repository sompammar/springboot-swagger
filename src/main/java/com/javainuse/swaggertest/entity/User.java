package com.javainuse.swaggertest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User Information", value = "User")
public class User {
    @ApiModelProperty(value = "firstName", notes = "First name of the user", example = "Jane")
    String firstName;

    @ApiModelProperty(value = "lastName", example = "Doe")
    String lastName;

    @ApiModelProperty(value = "id", example = "id", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    String id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

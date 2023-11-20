package com.nt.microservice.exception.core.usecase.service.user;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Ravikumar
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommand implements Serializable {

	// NotNull is always check the null condition only.
	// NotEmpty is check both the null and empty of string

	private static final long serialVersionUID = -3636999178997251142L;

	// @NotNull(message = "userId is cannot be null")
	@NotEmpty(message = "userId is cannot be null")
	private String userId;

	// @NotNull(message = "firstName cannot be null")
	@NotEmpty(message = "firstName is cannot be null")
	@JsonProperty("surname")
	private String firstName;

	// @NotNull(message ="lastName cannot be null")
	@NotEmpty(message = "lastName is cannot be null")
	private String lastName;

}

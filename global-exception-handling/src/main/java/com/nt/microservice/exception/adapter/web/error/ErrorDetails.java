package com.nt.microservice.exception.adapter.web.error;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ravi kumar
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = 7356645036480901197L;
	private LocalDateTime timestamp;
	private int status;
	private String message;
	private Object details;
}

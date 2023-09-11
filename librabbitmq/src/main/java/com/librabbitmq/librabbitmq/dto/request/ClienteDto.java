package com.librabbitmq.librabbitmq.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ClienteDto implements Serializable{
	private static final long serialVersionUID = 6319336915360336657L;
	
	private String name;
	private String message;
}

package com.aleph.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	private String ic;
	private String name;
	private String age;
	private String dob;
	private String addressLine1;
	private String addressLine2;
}

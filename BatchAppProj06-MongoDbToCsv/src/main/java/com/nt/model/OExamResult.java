package com.nt.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OExamResult {
	@Id
	private Integer id;
	private String dob;
	private Integer semester;
	private Double percentage;
}

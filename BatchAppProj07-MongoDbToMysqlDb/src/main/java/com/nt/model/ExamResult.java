package com.nt.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResult {
	@Id
	private Integer id;
	private LocalDate dob;
	private Integer semester;
	private Double percentage;
}

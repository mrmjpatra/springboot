package com.nt.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	public String custName;
	public String addrs;
	public Float billAmt;
//	public String[] education;
//	public List<String> favColors;
//	public Set<String> mobileNumbers;
//	public Map<String, Integer> documents;
//	public Company details;
}

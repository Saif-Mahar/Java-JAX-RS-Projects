package com.shashi.customer.model;

import com.shashi.customer.model.Customer.CustomerBuilder;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



// 	This POJO is for Header Object for JSON

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HeaderInfo {
	private String authToken;
	private String appName;
	private String appVersion;
	private String contentType;
}

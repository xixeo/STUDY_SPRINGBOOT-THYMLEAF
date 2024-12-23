package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class BoardDTO {

	private int num;
	
	private String name;
	
	private String subject;
	
	private String content;
	
	private int readcount;
	
	private Timestamp date;

	public String file;
}

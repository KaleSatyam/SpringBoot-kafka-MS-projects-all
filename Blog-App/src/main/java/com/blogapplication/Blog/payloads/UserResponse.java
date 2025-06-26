package com.blogapplication.Blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserResponse {

	
	private List<Userdto> content;
	private int pagenumber;
	private int pagesize;
	private long totalelements;
	private long totalpages;
	private boolean lastpage;
	private boolean firstpage;
}

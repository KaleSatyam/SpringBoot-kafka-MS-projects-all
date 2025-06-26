package com.blogapplication.Blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	private List<PostDto> content;
	private int pagenumber;
	private int pagesize;
	private long totalelements;
	private long totalpages;
	private boolean lastpage;
	private boolean firstpage;
	
}

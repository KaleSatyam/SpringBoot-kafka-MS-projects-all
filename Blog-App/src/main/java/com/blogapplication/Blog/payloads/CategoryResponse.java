package com.blogapplication.Blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {
	
	private List<CategoryDto> content;
	private int pagenumber;
	private int pagesize;
	private long totalelements;
	private long totalpages;
	private boolean lastpage;
	private boolean firstpage;

}

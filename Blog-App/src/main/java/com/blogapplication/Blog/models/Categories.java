package com.blogapplication.Blog.models;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryid;
	
	@Column(nullable = false,name = "title")
	private String categotyTitle;
	
	@Column(name = "description")
	private String categorydescription;
	
	@OneToMany(mappedBy = "categories" , cascade = CascadeType.ALL)
//	@JsonManagedReference
	private Set<Post> post=new HashSet<Post>();
	

}

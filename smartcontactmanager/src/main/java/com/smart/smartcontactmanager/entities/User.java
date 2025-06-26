package com.smart.smartcontactmanager.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name = "user_smartcontact")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@NotBlank(message = "Name required !!!")
	@Size(min = 3,max=20,message = "min 3 and max 20 !!!")
	private String Name;
	
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "invalid email please enter valid email ")
	@Column(unique = true)
	private String Email;

	@Pattern(regexp = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,}$",
	message = "Invalid password contains atleast 8 characters, atleast 1 digit,atleast 1 uppercase and lowercase,at least 1 special character ")
	private String Password;
	private String Role;
	private boolean Enabled;
	private String Imageurl; 
	@Column(length = 500)
	private String Aboutuser; 
	private String Address;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	private List<Contact> contact=new ArrayList<Contact>();
	
	
	
	public User(int id, String name, String email,String password, String role, boolean enabled, String imageurl, String aboutuser,
			String address) {
		super();
		Id = id;
		Name = name;
		Email = email;
		Password=password;
		Role = role;
		Enabled = enabled;
		Imageurl = imageurl;
		Aboutuser = aboutuser;
		Address = address;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public boolean isEnabled() {
		return Enabled;
	}
	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}
	public String getImageurl() {
		return Imageurl;
	}
	public void setImageurl(String imageurl) {
		Imageurl = imageurl;
	}
	public String getAboutuser() {
		return Aboutuser;
	}
	public void setAboutuser(String aboutuser) {
		Aboutuser = aboutuser;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public List<Contact> getContact() {
		return contact;
	}
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	
	
	@Override
	public String toString() {
		return "User [Id=" + Id + ", Name=" + Name + ", Email=" + Email + ", Role=" + Role + ", Enabled=" + Enabled
				+ ", Imageurl=" + Imageurl + ", Aboutuser=" + Aboutuser + ", Address=" + Address + "]";
	}
	
	
}

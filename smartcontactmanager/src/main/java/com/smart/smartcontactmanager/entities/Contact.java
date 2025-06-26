package com.smart.smartcontactmanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Contact")
public class Contact {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String Name;
	private String Nickname;
	private String Work;
	private String Email;
	private String phonenumber;
	private String Image;
	@Column(length = 500)
	private String Description;
	
	@ManyToOne
	private User user;
	
	
	
	public Contact(int id, String name, String nickname, String work, String email, String phonenumber, String image,
			String description) {
		super();
		Id = id;
		Name = name;
		Nickname = nickname;
		Work = work;
		Email = email;
		this.phonenumber = phonenumber;
		Image = image;
		Description = description;
	}
	public Contact() {
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
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public String getWork() {
		return Work;
	}
	public void setWork(String work) {
		Work = work;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Contact [Id=" + Id + ", Name=" + Name + ", Nickname=" + Nickname + ", Work=" + Work + ", Email=" + Email
				+ ", phonenumber=" + phonenumber + ", Image=" + Image + ", Description=" + Description + ", User ="+user+"]";
	}
	
	
	
}

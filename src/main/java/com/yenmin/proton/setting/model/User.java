package com.yenmin.proton.setting.model;


	import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

	
	public class User {
		
		@Id
		private String id;
		@NotBlank
		private String firstname;
		private String lastname;
		@NotBlank
		private String username;
		private String password;
		@NotBlank
		@Email
		private String email;
		private String image;
		@Field("last_modified_date")
		private Date lastModifiedDate;
		@Field("created_date")
		private Date createdDate;
		private Date lastlogin ;
		private boolean status = true; 
		private String token;
		private LocalDateTime tokenCreationDate;
		
		public User() {
		  }	
		public User(String firstname, String lastname, String username, String password, String email) {
			this.firstname = firstname;
			this.lastname = lastname;
			this.username = username;
			this.password = password;
			this.email = email;
		}
			
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public LocalDateTime getTokenCreationDate() {
			return tokenCreationDate;
		}
		public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
			this.tokenCreationDate = tokenCreationDate;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public boolean getStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createddate) {
			this.createdDate = createddate;
		}
		public Date getLastModifiedDate() {
			return lastModifiedDate;
		}
		public void setLastModifiedDate(Date lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
		}
		public Date getLastlogin() {
			return lastlogin;
		}
		public void setLastlogin(Date lastlogin) {
			this.lastlogin = lastlogin;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}	

}

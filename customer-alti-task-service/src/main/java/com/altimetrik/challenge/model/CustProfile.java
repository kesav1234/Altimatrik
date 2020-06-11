package com.altimetrik.challenge.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name="Userprofile")
public class CustProfile extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Automatically generated id of the userprofile")
    private Long Id;

   
    @ApiModelProperty(notes = "Information about the user")
    private String about;

    
    @ApiModelProperty(notes = "Url for the profile pic")
    private String profilePicUrl;

  
    @ApiModelProperty(notes = "Gender represeted in binary terms")
    private boolean gender;

   
   
    @ApiModelProperty(notes = "name of the customer")
    private String name;
    
    @ApiModelProperty(notes = "designation of the customer")
    private String designation;
    
    @ApiModelProperty(notes = "place of the customer")
    private String place;
    
    @ApiModelProperty(notes = "country of the customer")
    private String country;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ApiModelProperty(notes = "Property linking the userprofile with the user")
    private Customer user;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

		public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}






}

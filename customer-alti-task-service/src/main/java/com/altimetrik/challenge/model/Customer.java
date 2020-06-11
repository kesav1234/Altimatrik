package com.altimetrik.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.sound.sampled.spi.AudioFileReader;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "User")
public class Customer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Automatically generated id of the user")
    private Long Id;

    @NonNull
    @ApiModelProperty(notes = "Email of the user for logging in")
    private String email;

   
    @ApiModelProperty(notes = "Fcmid of the user mobile device")
    private String fcmId;
    
    @ApiModelProperty(notes = "name of the customer")
    private String name;
    
    @ApiModelProperty(notes = "designation of the customer")
    private String designation;
    
    @ApiModelProperty(notes = "place of the customer")
    private String place;
    
    @ApiModelProperty(notes = "country of the customer")
    private String country;

    @ElementCollection(fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "Roles that user is going to have")
    List<Role> roles;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JsonManagedReference
    @JsonIgnore
    @ApiModelProperty(notes = "Variable linking the user with his user profile")
    private CustProfile userProfile;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFcmId() {
		return fcmId;
	}

	public void setFcmId(String fcmId) {
		this.fcmId = fcmId;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public CustProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(CustProfile userProfile) {
		this.userProfile = userProfile;
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




}

package com.altimetrik.challenge.model;



import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Created by kesavulu.
 */
@Entity
@Data
@EqualsAndHashCode
@Table(name = "comments")
public class Comment extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated Comment ID")
    private Long id;

   
    @Lob
    @ApiModelProperty(notes = "Textual content of the comment")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("post_id")
    @ApiModelProperty(notes = "This is the post id of the post for which the comment belongs")
    private Question post;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_prof_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("user_prof_id")
    @ApiModelProperty(notes = "This is the user profile id of the user who wrote the comment")
    private CustProfile userProfile;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Question getPost() {
		return post;
	}


	public void setPost(Question post) {
		this.post = post;
	}


	public CustProfile getUserProfile() {
		return userProfile;
	}


	public void setUserProfile(CustProfile userProfile) {
		this.userProfile = userProfile;
	}



}


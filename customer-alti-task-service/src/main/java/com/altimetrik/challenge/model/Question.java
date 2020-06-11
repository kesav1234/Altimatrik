package com.altimetrik.challenge.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;



@SqlResultSetMapping(
        name = "findAllDataMapping",
        classes = @ConstructorResult(
                targetClass = com.altimetrik.challenge.dto.PostDto.class,
                columns = {
                        @ColumnResult(name = "id",type=Long.class),
                        @ColumnResult(name = "title",type= String.class),
                        @ColumnResult(name = "description",type=String.class),
                        @ColumnResult(name = "content",type=String.class),
                        @ColumnResult(name = "custId",type=Long.class),
                        @ColumnResult(name = "profile_Pic_Url",type=String.class)
                }
        )
)



@Entity
@Table(name = "posts")
public class Question extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

 
    private String description;

    
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_prof_id", nullable = false)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("user_prof_id")
    private CustProfile userProfile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CustProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(CustProfile userProfile) {
		this.userProfile = userProfile;
	}



}


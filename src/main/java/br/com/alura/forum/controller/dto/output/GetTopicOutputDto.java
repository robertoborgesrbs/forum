package br.com.alura.forum.controller.dto.output;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;

public class GetTopicOutputDto {

	private Long id;
	private String shortDescription;
	private String content;
	private TopicStatus status;
	private Instant creationInstant;
	private Instant lastUpdate;
	
	private String courseName;
	private String subcategoryName;
	private String categoryName;
	private String ownerName;
	
	private int numberOfResponse;
	private List<AnswerOutputDto> answers = new ArrayList<>();
	
	public GetTopicOutputDto(Topic topic) {
		this.id = topic.getId();
		this.shortDescription = topic.getShortDescription();
		this.content = topic.getContent();
		this.status = topic.getStatus();
		this.creationInstant = topic.getCreationInstant();
		this.lastUpdate = topic.getLastUpdate();
		this.courseName = topic.getCourse().getName();
		this.subcategoryName = topic.getCourse().getSubcategory().getName();
		this.categoryName = topic.getCourse().getCategoryName();
		this.ownerName = topic.getOwner().getName();		
		this.numberOfResponse = topic.getNumberOfAnswers();
		
		List<AnswerOutputDto> answers = AnswerOutputDto.listFromAnswers(topic.getAnswers());
		this.answers.addAll(answers);
	}
	

	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public Instant getCreationInstant() {
		return creationInstant;
	}

	public void setCreationInstant(Instant creationInstant) {
		this.creationInstant = creationInstant;
	}

	public Instant getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Instant lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getNumberOfResponse() {
		return numberOfResponse;
	}

	public void setNumberOfResponse(int numberOfResponse) {
		this.numberOfResponse = numberOfResponse;
	}
	
	public List<AnswerOutputDto> getAnswers() {
		return answers;
	}
	
	
}

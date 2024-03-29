package br.com.alura.forum.controller.dto.output;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.Answer;


public class AnswerOutputDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String content;
    private Instant creationTime;
    private boolean solution;
    private String ownerName;
    
    public AnswerOutputDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.creationTime = answer.getCreationTime();
        this.solution = answer.isSolution();
        this.ownerName = answer.getOwnerName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isSolution() {
        return solution;
    }

    public void setSolution(boolean solution) {
        this.solution = solution;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public static List<AnswerOutputDto> listFromAnswers(List<Answer> answers) {
        return answers.stream()
                .map(AnswerOutputDto::new)
                .collect(Collectors.toList());
    }
}

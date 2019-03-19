package org.edwith.todo.dto;

import org.edwith.todo.util.DateFormatChange;

public class TodoDto {
	private Long id;
	private String title;
	private String name;
	private int sequence;
	private String type;
	private String regdate;
	
	public TodoDto() {
		super();
	}

	public TodoDto(Long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public TodoDto(String title, String name, int sequence) {
		super();
		this.title = title;
		this.name = name;
		this.sequence = sequence;
	}

	public TodoDto(Long id, String title, String name, int sequence, String type, String regdate) {
		super();
		this.id = id;
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.type = type;
		this.regdate = regdate;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegdate() {
		return DateFormatChange.change(regdate);
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", title=" + title + ", name=" + name + ", sequence=" + sequence + ", type=" + type
				+ ", regdate=" + regdate + "]";
	}
	
	
}

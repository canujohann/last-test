package com.example.alartest.DB.domain;

/**
 * USER POJO
 * @author canu
 */
public class User {
	
	private long id;
	private String name;
	private int score;
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
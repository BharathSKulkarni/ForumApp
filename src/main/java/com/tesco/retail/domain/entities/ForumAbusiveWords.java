package com.tesco.retail.domain.entities;

import javax.persistence.*;

@Table(name = "ForumAbuseWords")
@Entity
@NamedQuery(name="ForumAbusiveWords.findAll",query="SELECT faw from ForumAbusiveWords faw")
public class ForumAbusiveWords {
	@Id
	private int abuseWordID;
	@Column(nullable = false)
	private String abuseWord;
	
	public int getAbuseWordID() {
		return abuseWordID;
	}
	public void setAbuseWordID(int abuseWordID) {
		this.abuseWordID = abuseWordID;
	}
	public String getAbuseWord() {
		return abuseWord;
	}
	public void setAbuseWord(String abuseWord) {
		this.abuseWord = abuseWord;
	}
	public ForumAbusiveWords(int abuseWordID, String abuseWord) {
		super();
		this.abuseWordID = abuseWordID;
		this.abuseWord = abuseWord;
	}
	public ForumAbusiveWords() {
		super();
	}
}

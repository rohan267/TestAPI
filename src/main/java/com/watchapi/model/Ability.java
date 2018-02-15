package com.watchapi.model;

import java.io.Serializable;

public class Ability implements Serializable {

	private int id;
	private String name;
	private String description;
	private boolean is_ultimate;
	private String url;
	private int heroid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIs_ultimate() {
		return is_ultimate;
	}
	public void setIs_ultimate(boolean is_ultimate) {
		this.is_ultimate = is_ultimate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getHeroid() {
		return heroid;
	}
	public void setHeroid(int heroid) {
		this.heroid = heroid;
	}
}
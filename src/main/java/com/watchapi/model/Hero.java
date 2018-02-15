package com.watchapi.model;

import java.io.Serializable;

//import javax.persistence.Entity;

//@Entity
public class Hero implements Serializable {

	private int id;
	private String name;
	private String description;
	private int health;
	private int armour;
	private int shield;
	private String real_name;
	private int age;
	private float height;
	private String affiliation;
	private String base_of_operations;
	private int difficulty;
	private String url;
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
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getArmour() {
		return armour;
	}
	public void setArmour(int armour) {
		this.armour = armour;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	public String getBase_of_operations() {
		return base_of_operations;
	}
	public void setBase_of_operations(String base_of_operations) {
		this.base_of_operations = base_of_operations;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
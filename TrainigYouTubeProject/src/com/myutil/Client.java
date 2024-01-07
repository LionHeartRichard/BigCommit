package com.myutil;

public class Client {
	private String name;
	private long money;
	private boolean activite;

	public Client() {
	}

	public Client(String name, long money, boolean activite) {
		this.name = name;
		this.money = money;
		this.activite = activite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public boolean isActivite() {
		return activite;
	}

	public void setActivite(boolean activite) {
		this.activite = activite;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", money: " + money + ", " + activite;
	}

}

package com.abc.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Giohang_ID implements Serializable{
	String manhathuoc;
	String masp;
	
	
	
	public Giohang_ID() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Giohang_ID(String manhathuoc, String masp) {
		super();
		this.manhathuoc = manhathuoc;
		this.masp = masp;
	}
	public String getManhathuoc() {
		return manhathuoc;
	}
	public void setManhathuoc(String manhathuoc) {
		this.manhathuoc = manhathuoc;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	
	
}

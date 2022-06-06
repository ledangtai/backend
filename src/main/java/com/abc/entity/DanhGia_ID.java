package com.abc.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DanhGia_ID implements Serializable{
	String manhathuoc;
	String masp;
	
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

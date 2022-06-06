package com.abc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Binhluan {
	@Id
	int id;
	String noidung;
	Date time;
	
	@ManyToOne
	@JoinColumn(name = "masp")
	Sanpham sanpham;
	
	@ManyToOne
	@JoinColumn(name = "manhathuoc")
	Nhathuoc nhathuoc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Sanpham getSanpham() {
		return sanpham;
	}

	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}

	public Nhathuoc getNhathuoc() {
		return nhathuoc;
	}

	public void setNhathuoc(Nhathuoc nhathuoc) {
		this.nhathuoc = nhathuoc;
	}

	

	
	
	
}

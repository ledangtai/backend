package com.abc.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
public class Giohang {
	@EmbeddedId
	Giohang_ID id;
	int soluong;
	
	@ManyToOne
	@MapsId("manhathuoc")
	@JoinColumn(name = "manhathuoc")
	Nhathuoc nhathuoc;
	
	@ManyToOne
	@MapsId("masp")
	@JoinColumn(name = "masp")
	Sanpham sanpham;

	public Giohang_ID getId() {
		return id;
	}

	public void setId(Giohang_ID id) {
		this.id = id;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	

	public Nhathuoc getNhathuoc() {
		return nhathuoc;
	}

	public void setNhathuoc(Nhathuoc nhathuoc) {
		this.nhathuoc = nhathuoc;
	}

	public Sanpham getSanpham() {
		return sanpham;
	}

	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}
	
	
}

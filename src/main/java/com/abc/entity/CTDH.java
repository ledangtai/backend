package com.abc.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CTDH {
	@EmbeddedId
	CTDH_ID id;
	int soluong;
	
	@JsonIgnore
	@ManyToOne
	@MapsId("madh")
	@JoinColumn(name = "madh")
	Donhang donhang;
	
	@ManyToOne
	@MapsId("masp")
	@JoinColumn(name="masp")
	Sanpham sanpham;

	public CTDH_ID getId() {
		return id;
	}

	public void setId(CTDH_ID id) {
		this.id = id;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public Donhang getDonhang() {
		return donhang;
	}

	public void setDonhang(Donhang donhang) {
		this.donhang = donhang;
	}

	public Sanpham getSanpham() {
		return sanpham;
	}

	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}
	
	
}

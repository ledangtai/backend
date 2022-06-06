package com.abc.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Donhang {
	@Id
	String madh;
	float tongtien;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	Date ngaydat;
	int trangthai;
	int hinhthucthanhtoan;
	
	
	@OneToMany(mappedBy = "donhang")
	List<CTDH> listCTDH;
	
	@ManyToOne
	@JoinColumn(name = "manv")
	Nhanvien nhanvien;
	
	@ManyToOne
	@JoinColumn(name="manhathuoc")
	Nhathuoc nhathuoc;

	public String getMadh() {
		return madh;
	}

	public void setMadh(String madh) {
		this.madh = madh;
	}

	public float getTongtien() {
		return tongtien;
	}

	public void setTongtien(float tongtien) {
		this.tongtien = tongtien;
	}

	public Date getNgaydat() {
		return ngaydat;
	}

	public void setNgaydat(Date ngaydat) {
		this.ngaydat = ngaydat;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public int getHinhthucthanhtoan() {
		return hinhthucthanhtoan;
	}

	public void setHinhthucthanhtoan(int hinhthucthanhtoan) {
		this.hinhthucthanhtoan = hinhthucthanhtoan;
	}

	public List<CTDH> getListCTDH() {
		return listCTDH;
	}

	public void setListCTDH(List<CTDH> listCTDH) {
		this.listCTDH = listCTDH;
	}

	public Nhanvien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public Nhathuoc getNhathuoc() {
		return nhathuoc;
	}

	public void setNhathuoc(Nhathuoc nhathuoc) {
		this.nhathuoc = nhathuoc;
	}

	
	
	
}

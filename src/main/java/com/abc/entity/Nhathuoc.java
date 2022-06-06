package com.abc.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Nhathuoc {
	@Id
	String manhathuoc;
	String tennhathuoc;
	String email;
	String sdt;
	String diachi;
	

	


	@JsonIgnore
	@OneToMany(mappedBy = "nhathuoc")
	List<Danhgia> listDG;
	
	@JsonIgnore
	@OneToMany(mappedBy = "nhathuoc")
	List<Binhluan> listBL;
	
	@JsonIgnore
	@OneToMany(mappedBy = "nhathuoc")
	List<Giohang> listGH;
	
	@JsonIgnore
	@OneToMany(mappedBy = "nhathuoc")
	List<Donhang> listDH;
	
	@ManyToOne
	@JoinColumn(name = "matk")
	Taikhoan taikhoan;
	public String getManhathuoc() {
		return manhathuoc;
	}

	public void setManhathuoc(String manhathuoc) {
		this.manhathuoc = manhathuoc;
	}

	public String getTennhathuoc() {
		return tennhathuoc;
	}

	public void setTennhathuoc(String tennhathuoc) {
		this.tennhathuoc = tennhathuoc;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Danhgia> getListDG() {
		return listDG;
	}

	public void setListDG(List<Danhgia> listDG) {
		this.listDG = listDG;
	}

	public List<Binhluan> getListBL() {
		return listBL;
	}

	public void setListBL(List<Binhluan> listBL) {
		this.listBL = listBL;
	}

	public List<Giohang> getListGH() {
		return listGH;
	}

	public void setListGH(List<Giohang> listGH) {
		this.listGH = listGH;
	}

	public List<Donhang> getListDH() {
		return listDH;
	}

	public void setListDH(List<Donhang> listDH) {
		this.listDH = listDH;
	}

	public Taikhoan getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(Taikhoan taikhoan) {
		this.taikhoan = taikhoan;
	}
	
	
	
}

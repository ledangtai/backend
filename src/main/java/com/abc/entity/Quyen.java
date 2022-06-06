package com.abc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quyen {
	@Id
	int maquyen;
	String tenquyen;
	
	@JsonIgnore
	@OneToMany(mappedBy = "quyen")
	List<Taikhoan> listTK;

	
	
	public Quyen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quyen(int maquyen) {
		this.maquyen = maquyen;
	}

	public int getMaquyen() {
		return maquyen;
	}

	public void setMaquyen(int maquyen) {
		this.maquyen = maquyen;
	}

	public String getTenquyen() {
		return tenquyen;
	}

	public void setTenquyen(String tenquyen) {
		this.tenquyen = tenquyen;
	}

	public List<Taikhoan> getListTK() {
		return listTK;
	}

	public void setListTK(List<Taikhoan> listTK) {
		this.listTK = listTK;
	}
	
	
}

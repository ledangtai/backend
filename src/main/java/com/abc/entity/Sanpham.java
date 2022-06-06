package com.abc.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Sanpham {
	@Id
	@Column(name="masp")
	String masp;
	@Column(name="tensp")
	String tensp;
	
	int soluong;
	float dongia;
	String mota_ngan;
	String mota_chitiet;
	String photo;
	float khuyenmai;
	

	@ManyToOne
	@JoinColumn(name = "MADM")
	Danhmuc danhmuc;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanpham")
	List<CTDH> listCTDH;
	
	@JsonIgnore
	@OneToMany(mappedBy ="sanpham")
	List<Danhgia> listDanhGia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanpham")
	List<Binhluan> listBinhLuan;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanpham")
	List<Giohang> listGioHang;

	public String getMasp() {
		return masp;
	}

	public void setMasp(String masp) {
		this.masp = masp;
	}

	public String getTensp() {
		return tensp;
	}

	public void setTensp(String tensp) {
		this.tensp = tensp;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public float getDongia() {
		return dongia;
	}

	public void setDongia(float dongia) {
		this.dongia = dongia;
	}

	public String getMota_ngan() {
		return mota_ngan;
	}

	public void setMota_ngan(String mota_ngan) {
		this.mota_ngan = mota_ngan;
	}

	public String getMota_chitiet() {
		return mota_chitiet;
	}

	public void setMota_chitiet(String mota_chitiet) {
		this.mota_chitiet = mota_chitiet;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public float getKhuyenmai() {
		return khuyenmai;
	}

	public void setKhuyenmai(float khuyenmai) {
		this.khuyenmai = khuyenmai;
	}

	public Danhmuc getDanhmuc() {
		return danhmuc;
	}

	public void setDanhmuc(Danhmuc danhmuc) {
		this.danhmuc = danhmuc;
	}

	public List<CTDH> getListCTDH() {
		return listCTDH;
	}

	public void setListCTDH(List<CTDH> listCTDH) {
		this.listCTDH = listCTDH;
	}

	public List<Danhgia> getListDanhGia() {
		return listDanhGia;
	}

	public void setListDanhGia(List<Danhgia> listDanhGia) {
		this.listDanhGia = listDanhGia;
	}

	public List<Binhluan> getListBinhLuan() {
		return listBinhLuan;
	}

	public void setListBinhLuan(List<Binhluan> listBinhLuan) {
		this.listBinhLuan = listBinhLuan;
	}

	public List<Giohang> getListGioHang() {
		return listGioHang;
	}

	public void setListGioHang(List<Giohang> listGioHang) {
		this.listGioHang = listGioHang;
	}
	public Sanpham(String masp, String tensp, int soluong, float dongia, String mota_ngan, String mota_chitiet,
			String photo, float khuyenmai, Danhmuc danhmuc) {
		super();
		this.masp = masp;
		this.tensp = tensp;
		this.soluong = soluong;
		this.dongia = dongia;
		this.mota_ngan = mota_ngan;
		this.mota_chitiet = mota_chitiet;
		this.photo = photo;
		this.khuyenmai = khuyenmai;
		this.danhmuc = danhmuc;
	}
	public Sanpham() {
		super();
	}
	
}

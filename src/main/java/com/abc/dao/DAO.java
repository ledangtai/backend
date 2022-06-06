package com.abc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.abc.model.DoanhThu;
import com.abc.model.Matkhau;

public class DAO {
	
	Connection con = null;

	public DAO() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databaseName=GetCare;username=sa;password=123");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public ArrayList<DoanhThu> getDoanhThu() {
		ArrayList<DoanhThu> list = new ArrayList<DoanhThu>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT MONTH(DONHANG.NGAYDAT),SUM(TONGTIEN) FROM DONHANG WHERE (DONHANG.TRANGTHAI = '3') AND (YEAR(DONHANG.NGAYDAT) = YEAR(GETDATE()))  GROUP BY MONTH(DONHANG.NGAYDAT)");
			while (rs.next()) {
				DoanhThu ds = new DoanhThu();
				ds.setThang(rs.getString(1));
				ds.setTien(rs.getInt(2));
				list.add(ds);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<Matkhau> getMatkhau() {
		ArrayList<Matkhau> list = new ArrayList<Matkhau>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select NHATHUOC.TENNHATHUOC, NHATHUOC.Email , TAIKHOAN.PASSWORD from NHATHUOC, TAIKHOAN where NHATHUOC.MATK = TAIKHOAN.MATK");
			while (rs.next()) {
				Matkhau mk = new Matkhau();
				mk.setTennhahthuoc(rs.getString(1));
				mk.setEmail(rs.getString(2));
				mk.setPassword(rs.getString(3));
				list.add(mk);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}

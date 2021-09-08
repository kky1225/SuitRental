// 2021-09-07 서준화
package kr.member.vo;

import java.sql.Date;

public class MemberVO {
	private int mem_num;
	private String id;
	private int auth;
	private String name;
	private String passwd;
	private String phone;
	private String email;
	private String zipcode;
	private String address1;
	private String address2;
	private String gender;
	private int rental_total;
	private int rental_now;
	private int non_return;
	private Date reg_date;
	private Date modify_date;
	
	public boolean isCheckedPassword(String userPasswd) {
		// 회원 등급(auth) : 0(탈퇴회원), 1(정지회원), 2(일반회원), 3(관리자)
		if(auth > 1 && passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getRental_total() {
		return rental_total;
	}
	public void setRental_total(int rental_total) {
		this.rental_total = rental_total;
	}
	public int getRental_now() {
		return rental_now;
	}
	public void setRental_now(int rental_now) {
		this.rental_now = rental_now;
	}
	public int getNon_return() {
		return non_return;
	}
	public void setNon_return(int non_return) {
		this.non_return = non_return;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
}

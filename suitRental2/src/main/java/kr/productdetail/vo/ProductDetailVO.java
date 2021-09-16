package kr.productdetail.vo;

import java.sql.Date;

public class ProductDetailVO {
	private int x_code;
	private String x_name;
	private String x_file;
	private int x_price;
	private int x_stock;
	private String x_gender;
	private String x_size;
	private String x_brand;
	private int x_rental_count;
	private int x_hit;
	private int x_like;
	private Date x_reg_date;
	private int x_purchase;
	private int x_purchase_cnt;
	private String x_type;
	private String x_contents;
	private int mem_num;
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getX_code() {
		return x_code;
	}
	public void setX_code(int x_code) {
		this.x_code = x_code;
	}
	public String getX_name() {
		return x_name;
	}
	public void setX_name(String x_name) {
		this.x_name = x_name;
	}
	public String getX_file() {
		return x_file;
	}
	public void setX_file(String x_file) {
		this.x_file = x_file;
	}
	public int getX_price() {
		return x_price;
	}
	public void setX_price(int x_price) {
		this.x_price = x_price;
	}
	public int getX_stock() {
		return x_stock;
	}
	public void setX_stock(int x_stock) {
		this.x_stock = x_stock;
	}
	public String getX_gender() {
		return x_gender;
	}
	public void setX_gender(String x_gender) {
		this.x_gender = x_gender;
	}
	public String getX_size() {
		return x_size;
	}
	public void setX_size(String x_size) {
		this.x_size = x_size;
	}
	public String getX_brand() {
		return x_brand;
	}
	public void setX_brand(String x_brand) {
		this.x_brand = x_brand;
	}
	public int getX_rental_count() {
		return x_rental_count;
	}
	public void setX_rental_count(int x_rental_count) {
		this.x_rental_count = x_rental_count;
	}
	public int getX_hit() {
		return x_hit;
	}
	public void setX_hit(int x_hit) {
		this.x_hit = x_hit;
	}
	public int getX_like() {
		return x_like;
	}
	public void setX_like(int x_like) {
		this.x_like = x_like;
	}
	public Date getX_reg_date() {
		return x_reg_date;
	}
	public void setX_reg_date(Date x_reg_date) {
		this.x_reg_date = x_reg_date;
	}
	
	public int getX_purchase() {
		return x_purchase;
	}
	public void setX_purchase(int x_purchase) {
		this.x_purchase = x_purchase;
	}
	
	
	public String getX_type() {
		return x_type;
	}
	public int getX_purchase_cnt() {
		return x_purchase_cnt;
	}
	public void setX_purchase_cnt(int x_purchase_cnt) {
		this.x_purchase_cnt = x_purchase_cnt;
	}
	public void setX_type(String x_type) {
		this.x_type = x_type;
	}
	public String getX_contents() {
		return x_contents;
	}
	public void setX_contents(String x_contents) {
		this.x_contents = x_contents;
	}
	
}

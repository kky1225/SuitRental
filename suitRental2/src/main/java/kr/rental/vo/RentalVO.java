package kr.rental.vo;

public class RentalVO {
	private int rentd_num;
	private int rent_num;
	private int x_code;
	private int mem_num;
	private String rental_date;
	private String return_date;
	private String rental_type;
	private String return_type;
	
	
	public int getRentd_num() {
		return rentd_num;
	}
	public void setRentd_num(int rentd_num) {
		this.rentd_num = rentd_num;
	}
	public int getRent_num() {
		return rent_num;
	}
	public void setRent_num(int rent_num) {
		this.rent_num = rent_num;
	}
	public int getX_code() {
		return x_code;
	}
	public void setX_code(int x_code) {
		this.x_code = x_code;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getRental_date() {
		return rental_date;
	}
	public void setRental_date(String rental_date) {
		this.rental_date = rental_date;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	public String getRental_type() {
		return rental_type;
	}
	public void setRental_type(String rental_type) {
		this.rental_type = rental_type;
	}
	public String getReturn_type() {
		return return_type;
	}
	public void setReturn_type(String return_type) {
		this.return_type = return_type;
	}
}

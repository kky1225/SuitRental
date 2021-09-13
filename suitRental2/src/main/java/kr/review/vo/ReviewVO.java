package kr.review.vo;

import java.sql.Date;

public class ReviewVO {
	private int review_num;
	private String title;
	private String content;
	private int hit;
	private Date reg_date;
	private Date modify_date;
	private String filename;
	private String ip;
	private int mem_num;
	private String id;
	private int x_code;
	
	public ReviewVO() {
		
	}

	public ReviewVO(int review_num, String title, String content, int hit, Date reg_date, Date modify_date,
			String filename, String ip, int mem_num, String id, int x_code) {
		this.review_num = review_num;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.modify_date = modify_date;
		this.filename = filename;
		this.ip = ip;
		this.mem_num = mem_num;
		this.id = id;
		this.x_code = x_code;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "ReviewVO [review_num=" + review_num + ", title=" + title + ", content=" + content + ", hit=" + hit
				+ ", reg_date=" + reg_date + ", modify_date=" + modify_date + ", filename=" + filename + ", ip=" + ip
				+ ", mem_num=" + mem_num + ", id=" + id + "]";
	}
}

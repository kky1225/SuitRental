package kr.board.vo;

import java.sql.Date;

public class BoardQnaVO {
	private int qna_num;		// �۹�ȣ
	private String title;		// ����
	private String q_content;	// ����
	private String a_content;	// �亯
	private int hit;			// ��ȸ��
	private Date reg_date;		// �����
	private Date ans_date;	// �亯��
	private String filename;	// ���ϸ�
	private String ip;			// ip�ּ�
	private int mem_num;		// ȸ����ȣ
	private String id;			// ȸ�� ���̵�
	
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQ_content() {
		return q_content;
	}
	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}
	public String getA_content() {
		return a_content;
	}
	public void setA_content(String a_content) {
		this.a_content = a_content;
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
	public Date getans_date() {
		return ans_date;
	}
	public void setans_date(Date ans_date) {
		this.ans_date = ans_date;
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
	
}

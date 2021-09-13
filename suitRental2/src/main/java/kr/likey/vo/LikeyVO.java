package kr.likey.vo;

public class LikeyVO {
	private int likey_num;
	private int x_code;
	private int mem_num;
	
	public LikeyVO() {
		
	}

	public LikeyVO(int likey_num, int x_code, int mem_num) {
		this.likey_num = likey_num;
		this.x_code = x_code;
		this.mem_num = mem_num;
	}

	public int getLikey_num() {
		return likey_num;
	}

	public void setLikey_num(int likey_num) {
		this.likey_num = likey_num;
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
	
}

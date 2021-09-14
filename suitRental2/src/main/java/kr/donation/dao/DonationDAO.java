package kr.donation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.donation.vo.DonationReplyVO;
import kr.donation.vo.DonationVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class DonationDAO {
	private static DonationDAO instance=new DonationDAO();
	
	public static DonationDAO getInstance() {
		return instance;
	}
	
	private DonationDAO() {};
	
	//글 등록
	public void insertDonation(DonationVO donation)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO xdonation (donation_num,title,content,filename,ip,mem_num) VALUES(xdonation_seq.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, donation.getTitle());
			pstmt.setString(2, donation.getContent());
			pstmt.setString(3, donation.getFilename());
			pstmt.setString(4, donation.getIp());
			pstmt.setInt(5, donation.getMem_num());
			
			pstmt.executeUpdate();
		
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//총 레코드 수
	public int getCount(String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			if(keyword == null || "".equals(keyword)) {
				//전체 글 갯수
				sql = "SELECT count(*) FROM xdonation d JOIN xmember m ON d.mem_num = m.mem_num";		
				pstmt = conn.prepareStatement(sql);
			}else {
				//검색 글 갯수
				if(keyfield.equals("1")) {
					sub_sql = "d.title LIKE ?";
				}else if(keyfield.equals("2")) {
					sub_sql = "m.id = ?";
				}else if(keyfield.equals("3")) {
					sub_sql="d.content LIKE ?";
				}
				sql = "SELECT COUNT(*) FROM xdonation d JOIN xmember m ON d.mem_num = m.mem_num WHERE " + sub_sql;
				pstmt=conn.prepareStatement(sql);
				if(keyfield.equals("2")) {
					pstmt.setString(1, keyword);
				}else {
					pstmt.setString(1, "%"+keyword+"%");
				}
			}
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	//글 목록
	public List<DonationVO> getList(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		List<DonationVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			if(keyword==null || "".equals(keyword)) {
				//전체 글 보기
				sql = "SELECT * FROM(SELECT a.*, rownum rnum FROM (SELECT * FROM xdonation d JOIN xmember m ON d.mem_num=m.mem_num ORDER BY d.donation_num DESC)a) WHERE rnum>=? AND rnum<=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}else {
				//검색 글 보기
				if(keyfield.equals("1")) {
					sub_sql="d.title LIKE ?";
				}else if(keyfield.equals("2")) {
					sub_sql="m.id=?";
				}else if(keyfield.equals("3")) {
					sub_sql="d.content LIKE ?";
				}
				
				sql = "SELECT * FROM(SELECT a.*, rownum rnum FROM (SELECT * FROM xdonation d JOIN xmember m ON d.mem_num=m.mem_num WHERE "+sub_sql+" ORDER BY d.donation_num DESC)a) WHERE rnum>=? AND rnum<=?";
				pstmt=conn.prepareStatement(sql);
				if(keyfield.equals("2")) {
					pstmt.setString(1, keyword);
				}else {
					pstmt.setString(1, "%"+keyword+"%");
				}
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
			}
			
			rs=pstmt.executeQuery();
			list = new ArrayList<DonationVO>();
			while(rs.next()) {
				DonationVO donation = new DonationVO();
				donation.setDonation_num(rs.getInt("donation_num"));
				donation.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				donation.setContent(rs.getString("content"));
				donation.setHit(rs.getInt("hit"));
				donation.setFilename(rs.getString("filename"));
				donation.setIp(rs.getString("ip"));
				donation.setReg_date(rs.getDate("reg_date"));
				donation.setModify_date(rs.getDate("modify_date"));
				donation.setMem_num(rs.getInt("mem_num"));
				donation.setMem_id(rs.getString("id"));
				
				list.add(donation);
			}
		
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		
		return list;
	}
	//상세 페이지
	public DonationVO getDonation(int donation_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		DonationVO donation = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM xdonation d JOIN xmember m ON d.mem_num=m.mem_num WHERE donation_num=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, donation_num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				donation = new DonationVO();
				donation.setDonation_num(rs.getInt("donation_num"));
				donation.setTitle(rs.getString("title"));
				donation.setContent(rs.getString("content"));
				donation.setHit(rs.getInt("hit"));
				donation.setFilename(rs.getString("filename"));
				donation.setIp(rs.getString("ip"));
				donation.setReg_date(rs.getDate("reg_date"));
				donation.setModify_date(rs.getDate("modify_date"));
				donation.setMem_num(rs.getInt("mem_num"));
				donation.setMem_id(rs.getString("id"));
				
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return donation;
	}
	//조회수 증가
	public void updateReadCount(int donation_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "UPDATE xdonation SET hit=hit+1 WHERE donation_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, donation_num);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	//글 수정
	public void updateDonation(DonationVO donation, int check)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			if(check == 1) {
				if(donation.getFilename() == null) {
					sql = "UPDATE xdonation SET title = ?, content = ?, ip = ?, modify_date = SYSDATE WHERE donation_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, donation.getTitle());
					pstmt.setString(2, donation.getContent());
					pstmt.setString(3, donation.getIp());
					pstmt.setInt(4, donation.getDonation_num());
				}else {
					sql = "UPDATE xdonation SET title = ?, content = ?, filename = ?, ip = ?, modify_date = SYSDATE WHERE donation_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, donation.getTitle());
					pstmt.setString(2, donation.getContent());
					pstmt.setString(3, donation.getFilename());
					pstmt.setString(4, donation.getIp());
					pstmt.setInt(5, donation.getDonation_num());
				}
			}else if(check == 0) {
				sql = "UPDATE xdonation SET title = ?, content = ?, filename = null, ip = ?, modify_date = SYSDATE WHERE donation_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, donation.getTitle());
				pstmt.setString(2, donation.getContent());
				pstmt.setString(3, donation.getIp());
				pstmt.setInt(4, donation.getDonation_num());
			}
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 삭제
	public void deleteDonation(int donation_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn=DBUtil.getConnection();
			//auto commit 해제
			conn.setAutoCommit(false);
			
			sql="DELETE FROM xdonation_reply  WHERE donation_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, donation_num);
			pstmt.executeUpdate();
			
			sql="DELETE FROM xdonation  WHERE donation_num=?";
			pstmt2=conn.prepareStatement(sql);
			pstmt2.setInt(1, donation_num);
			pstmt2.executeUpdate();
			
			//모든 sql문의 실행이 성공
			conn.commit();
			
		}catch(Exception e) {
			//sql문이 하나라도 실패하면 롤백
			conn.rollback();
		 throw new Exception(e);	
		}finally {
			DBUtil.executeClose(null, pstmt, null);
			DBUtil.executeClose(null, pstmt2, conn);
		}
	}
	
	//댓글 등록
	public void insertDonationReply(DonationReplyVO donationReplyVo)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO xdonation_reply (re_num,re_content,re_ip,mem_num,donation_num) VALUES(donation_reply_seq.nextval,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, donationReplyVo.getRe_content());
			pstmt.setString(2, donationReplyVo.getRe_ip());
			pstmt.setInt(3, donationReplyVo.getMem_num());
			pstmt.setInt(4, donationReplyVo.getDonation_num());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//댓글 수
	public int getDonationReplyCount(int donation_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT count(*) FROM xdonation_reply r JOIN xmember m ON r.mem_num=m.mem_num WHERE r.donation_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, donation_num);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count =rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		
		return count;
	}
	//댓글 목록
	public List<DonationReplyVO> getDonationReply(int start, int end, int donation_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		List<DonationReplyVO> list = null;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM (SELECT a.*,rownum rnum FROM(SELECT d.re_num, TO_CHAR(d.re_date,'YYYY-MM-DD HH24:MI:SS') re_date, TO_CHAR(d.re_modifydate,'YYYY-MM-DD HH24:MI:SS') re_modifydate, d.re_content, d.mem_num, d.donation_num, m.id FROM xdonation_reply d JOIN xmember m ON d.mem_num=m.mem_num WHERE d.donation_num=? ORDER BY d.re_num DESC)a) WHERE rnum>=? AND rnum<=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, donation_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			list = new ArrayList<DonationReplyVO>();
			rs=pstmt.executeQuery();
			while(rs.next()) {
				DonationReplyVO reply = new DonationReplyVO();
				reply.setRe_num(rs.getInt("re_num"));
				reply.setRe_content(rs.getString("re_content"));
				reply.setMem_num(rs.getInt("mem_num"));
				reply.setDonation_num(rs.getInt("donation_num"));
				reply.setId(rs.getString("id"));
				reply.setRe_date(DurationFromNow.getTimeDiffLabel(rs.getString("re_date")));
				reply.setRe_modifydate(DurationFromNow.getTimeDiffLabel(rs.getString("re_modifydate")));
				
				list.add(reply);
				
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
		
	}
	//댓글 수정
	public void updateDonationReply(DonationReplyVO reply)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn=DBUtil.getConnection();
			sql="UPDATE xdonation_reply SET re_content=?, re_ip=?, re_modifydate=SYSDATE WHERE re_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, reply.getRe_content());
			pstmt.setString(2, reply.getRe_ip());
			pstmt.setInt(3, reply.getRe_num());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//댓글 삭제
	public void deleteDonationReply(int re_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn=DBUtil.getConnection();
			sql="DELETE FROM xdonation_reply WHERE re_num=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, re_num);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}

package kr.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.review.vo.ReviewVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class ReviewDAO {
	private static ReviewDAO instance = new ReviewDAO();
	
	public static ReviewDAO getInstance() {
		return instance;
	}
	
	public ReviewDAO() {
		
	}
	
	public void reviewInsert(ReviewVO reviewVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO xreview(review_num, title, content, filename, ip, mem_num, x_code) VALUES (xreview_seq.nextval, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewVO.getTitle());
			pstmt.setString(2, reviewVO.getContent());
			pstmt.setString(3, reviewVO.getFilename());
			pstmt.setString(4, reviewVO.getIp());
			pstmt.setInt(5, reviewVO.getMem_num());
			pstmt.setInt(6, reviewVO.getX_code());
			
			pstmt.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	public int getReviewCount(String keyfield, String keyword, int x_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(x_code == 0) {
				sql = "SELECT COUNT(*) FROM xreview r, xmember m WHERE r.mem_num = m.mem_num";
				pstmt = conn.prepareStatement(sql);
				if(keyword == null || keyword.equals("")) {
					
				}else {
					if(keyfield.equals("1")) sub_sql = "r.title LIKE ?";
					else if(keyfield.equals("2")) sub_sql = "m.id LIKE ?";
					else if(keyfield.equals("3")) sub_sql = "r.content LIKE ?";
					
					sql = "SELECT COUNT(*) FROM xreview r, xmember m WHERE r.mem_num = m.mem_num AND " + sub_sql;
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + keyword + "%");
				}
			}else {
				if(keyword == null || keyword.equals("")) {
					sql = "SELECT COUNT(*) FROM xreview r, xmember m WHERE r.mem_num = m.mem_num AND x_code = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, x_code);
				}else {
					if(keyfield.equals("1")) sub_sql = "r.title LIKE ?";
					else if(keyfield.equals("2")) sub_sql = "m.id LIKE ?";
					else if(keyfield.equals("3")) sub_sql = "r.content LIKE ?";
					
					sql = "SELECT COUNT(*) FROM xreview r, xmember m WHERE r.mem_num = m.mem_num AND " + sub_sql;
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + keyword + "%");
				}
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	public List<ReviewVO> getListReview(int start, int end, String keyfield, String keyword){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		List<ReviewVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword == null || keyword.equals("")) {
				sql = "SELECT * FROM (SELECT a.*, rownum AS rnum FROM (SELECT * FROM xreview r JOIN xmember m ON r.mem_num = m.mem_num ORDER BY r.review_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
					
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}else {
				if(keyfield.equals("1")) sub_sql = "r.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "r.content LIKE ?";
				
				sql = "SELECT * FROM (SELECT a.*, rownum AS rnum FROM (SELECT * FROM xreview r JOIN xmember m ON r.mem_num = m.mem_num WHERE " + sub_sql + " ORDER BY r.review_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, "%" + keyword + "%");
	
				
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			}
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<ReviewVO>();
			
			while(rs.next()) {
				ReviewVO reviewVO = new ReviewVO();
				reviewVO.setReview_num(rs.getInt("review_num"));
				reviewVO.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				reviewVO.setContent(StringUtil.useBrNoHtml(rs.getString("content")));
				reviewVO.setHit(rs.getInt("hit"));
				reviewVO.setReg_date(rs.getDate("reg_date"));
				reviewVO.setModify_date(rs.getDate("modify_date"));
				reviewVO.setFilename(rs.getString("filename"));
				reviewVO.setIp(rs.getString("ip"));
				reviewVO.setMem_num(rs.getInt("mem_num"));
				reviewVO.setId(rs.getString("id"));
				
				list.add(reviewVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public List<ReviewVO> getListReview(int start, int end, int x_code){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<ReviewVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum AS rnum FROM (SELECT * FROM xreview r JOIN xmember m ON r.mem_num = m.mem_num ORDER BY r.review_num DESC)a) WHERE rnum >=? AND rnum <= ? AND x_code = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			pstmt.setInt(3, x_code);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<ReviewVO>();
			
			while(rs.next()) {
				ReviewVO reviewVO = new ReviewVO();
				reviewVO.setReview_num(rs.getInt("review_num"));
				reviewVO.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				reviewVO.setContent(StringUtil.useBrNoHtml(rs.getString("content")));
				reviewVO.setHit(rs.getInt("hit"));
				reviewVO.setReg_date(rs.getDate("reg_date"));
				reviewVO.setModify_date(rs.getDate("modify_date"));
				reviewVO.setFilename(rs.getString("filename"));
				reviewVO.setIp(rs.getString("ip"));
				reviewVO.setMem_num(rs.getInt("mem_num"));
				reviewVO.setId(rs.getString("id"));
				
				list.add(reviewVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public ReviewVO getReview(int review_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ReviewVO reviewVO = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM xreview r, xmember m WHERE r.mem_num = m.mem_num AND r.review_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reviewVO = new ReviewVO();
				reviewVO.setReview_num(rs.getInt("review_num"));
				reviewVO.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				reviewVO.setContent(rs.getString("content"));
				reviewVO.setHit(rs.getInt("hit"));
				reviewVO.setReg_date(rs.getDate("reg_date"));
				reviewVO.setModify_date(rs.getDate("modify_date"));
				reviewVO.setFilename(rs.getString("filename"));
				reviewVO.setIp(rs.getString("ip"));
				reviewVO.setMem_num(rs.getInt("mem_num"));
				reviewVO.setId(rs.getString("id"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return reviewVO;
	}
	
	public void updateReadcount(int review_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE xreview SET hit = hit + 1 WHERE review_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			
			pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	public void reviewUpdate(ReviewVO reviewVO, int check) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			if(check == 1) {
				if(reviewVO.getFilename() == null) {
					sql = "UPDATE xreview SET title = ?, content = ?, ip = ?, modify_date = SYSDATE WHERE review_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, reviewVO.getTitle());
					pstmt.setString(2, reviewVO.getContent());
					pstmt.setString(3, reviewVO.getIp());
					pstmt.setInt(4, reviewVO.getReview_num());
				}else {
					sql = "UPDATE xreview SET title = ?, content = ?, filename = ?, ip = ?, modify_date = SYSDATE WHERE review_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, reviewVO.getTitle());
					pstmt.setString(2, reviewVO.getContent());
					pstmt.setString(3, reviewVO.getFilename());
					pstmt.setString(4, reviewVO.getIp());
					pstmt.setInt(5, reviewVO.getReview_num());
				}
			}else if(check == 0) {
				sql = "UPDATE xreview SET title = ?, content = ?, filename = null, ip = ?, modify_date = SYSDATE WHERE review_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reviewVO.getTitle());
				pstmt.setString(2, reviewVO.getContent());
				pstmt.setString(3, reviewVO.getIp());
				pstmt.setInt(4, reviewVO.getReview_num());
			}
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	public void reviewDelete(int review_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM xreview WHERE review_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			
			pstmt.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	public int checkReview(int x_code, int mem_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM xreview WHERE x_code = ? AND mem_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, x_code);
			pstmt.setInt(2, mem_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = count + 1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		return count;
	}
}

package kr.likey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.likey.vo.LikeyVO;
import kr.util.DBUtil;

public class LikeyDAO {
private static LikeyDAO instance = new LikeyDAO();
	
	public static LikeyDAO getInstance() {
		return instance;
	}
	
	public LikeyDAO() {
		
	}
	
	public void likeyUp(int x_code, int user_num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			sql = "INSERT INTO xlikey VALUES (xlikey_seq.nextval, ?, ?)";
			
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, x_code);
			pstmt1.setInt(2, user_num);
			
			pstmt1.executeQuery();
			
			sql = "UPDATE suit SET x_like = x_like + 1 WHERE x_code= ?";

			pstmt2=conn.prepareStatement(sql);
			pstmt2.setInt(1, x_code);
			
			pstmt2.executeQuery();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, conn);
		}
	}
	
	public void likeyDown(int x_code, int user_num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			sql = "DELETE FROM xlikey WHERE x_code = ? AND mem_num = ?";
			
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, x_code);
			pstmt1.setInt(2, user_num);
			
			pstmt1.executeQuery();
			
			sql = "UPDATE suit SET x_like = x_like - 1 WHERE x_code= ?";

			pstmt2=conn.prepareStatement(sql);
			pstmt2.setInt(1, x_code);
			
			pstmt2.executeQuery();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, conn);
		}
	}
	
	public LikeyVO getLikey(int x_code, int user_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		LikeyVO likeyVO = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM xlikey WHERE x_code = ? AND mem_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, x_code);
			pstmt.setInt(2, user_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				likeyVO = new LikeyVO();
				
				likeyVO.setLikey_num(rs.getInt("likey_num"));
				likeyVO.setX_code(rs.getInt("x_code"));
				likeyVO.setMem_num(rs.getInt("mem_num"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return likeyVO;
	}
}

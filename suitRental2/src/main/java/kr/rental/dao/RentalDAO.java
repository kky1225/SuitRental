package kr.rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.rental.vo.RentalVO;
import kr.util.DBUtil;

public class RentalDAO {
	
		private static RentalDAO instance = new RentalDAO();
		
		public static RentalDAO getInstance() {
			return instance;
		}
		
		private RentalDAO() {}
		
		
		// 대여하기
		public int rent(RentalVO dto) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String sql = null;
			
			int result=0;
			
			try {
				conn = DBUtil.getConnection();
				sql = "INSERT INTO rental VALUES (rental_seq.nextval, ?, ?, ?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, dto.getX_code());
				pstmt.setInt(2, dto.getMem_num());
				pstmt.setString(3, dto.getRental_date());
				pstmt.setString(4, dto.getReturn_date());
				pstmt.setString(5, dto.getRental_type());
				pstmt.setString(6, dto.getReturn_type());
				
				
				result=pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
			
			return result;
		}
		
		
		
		
		
		
}

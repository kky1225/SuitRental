package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public MemberDAO() {
		
	}
	
	public MemberVO checkMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MemberVO memberVO = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM xmember m, xmember_detail d WHERE m.mem_num = d.mem_num AND id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_num(rs.getInt("mem_num"));
				memberVO.setId(rs.getString("id"));
				memberVO.setPasswd(rs.getString("passwd"));
				memberVO.setAuth(rs.getInt("auth"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return memberVO;
	}

	public void insertMember(MemberVO memberVO) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0; 
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			sql = "SELECT xmember_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			sql = "INSERT INTO xmember(mem_num, id) VALUES (?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, num);
			pstmt2.setString(2, memberVO.getId());
			pstmt2.executeUpdate();
			
			sql = "INSERT INTO xmember_detail (mem_num, name, passwd, phone, email, zipcode, address1, address2, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, num);
			pstmt3.setString(2, memberVO.getName());
			pstmt3.setString(3, memberVO.getPasswd());
			pstmt3.setString(4, memberVO.getPhone());
			pstmt3.setString(5, memberVO.getEmail());
			pstmt3.setString(6, memberVO.getZipcode());
			pstmt3.setString(7, memberVO.getAddress1());
			pstmt3.setString(8, memberVO.getAddress2());
			pstmt3.setString(9, memberVO.getGender());
			pstmt3.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
		    conn.rollback();
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	public MemberVO getMember(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MemberVO member = null;
			
		try {
			conn = DBUtil.getConnection();
				
			sql = "select * from xmember m join xmember_detail d on m.mem_num = d.mem_num where m.mem_num=?";
				
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, mem_num);
				
				
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setZipcode(rs.getString("zipcode"));
				member.setAddress1(rs.getString("address1"));
				member.setAddress2(rs.getString("address2"));
				member.setGender(rs.getString("gender"));
				member.setReg_date(rs.getDate("reg_date"));// ������
				member.setModify_date(rs.getDate("modify_date"));//	������
			}
				
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			// �ڿ�����
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	
	// 회원 정보 수정
		public void updateMember(MemberVO member) throws Exception{
			// 수정 가능 : name,phone,email,zipcode,address1,address2,modify_date=SYSDATE
			// PK : mem_num
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				// 커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				// 수정 가능 : name,phone,email,zipcode,address1,address2,modify_date=SYSDATE
				// SQL문 작성
				sql = "update xmember_detail set name=?,phone=?,email=?,zipcode=?,address1=?,address2=?,"
						+ "modify_date=SYSDATE where mem_num=?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setString(1,member.getName());
				pstmt.setString(2,member.getPhone());
				pstmt.setString(3,member.getEmail());
				pstmt.setString(4,member.getZipcode());
				pstmt.setString(5,member.getAddress1());
				pstmt.setString(6,member.getAddress2());
				pstmt.setInt(7, member.getMem_num());
				
				// SQL문 실행
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				// 자원 정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		// 비밀번호 수정
		public void updatePassword(String passwd, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				// 커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				
				// SQL문 작성
				sql = "update xmember_detail set passwd=? where mem_num=?";
				
				// PreparedStatement 객체를 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setString(1, passwd);	// 새 비밀번호
				pstmt.setInt(2, mem_num);	// 회원번호
				
				// SQL문 실행
				pstmt.executeUpdate();
				
			}catch (Exception e) {
				throw new Exception(e);
			}finally {
				// 자원 정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		// 회원 탈퇴(회원 정보 삭제)
		public void deleteMember(int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			String sql = null;
			
			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// 오토커밋 해제
				conn.setAutoCommit(false);
				
				// SQL문 작성 zmember의 auth 값 변경
				sql = "update xmember set auth=0 where mem_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_num);
				pstmt.executeUpdate();
				
				// SQL문 작성 zmember_detail의 레코드 삭제
				sql = "delete from xmember_detail where mem_num=?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, mem_num);
				pstmt2.executeUpdate();
				
				// 모든 SQL문의 실행이 성공
				conn.commit();
			}catch (Exception e) {
				// SQL 문장이 하나라도 실패하면 rollback;
				conn.rollback();
				throw new Exception(e);
			}finally {
				// 자원 정리
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		// 대여 목록 호출
		
		// 대여 취소
		
		// 대여 정보 변경
		
		// 회원 등급(auth) 변경 -> 대여금지(2)로 변경하는 메소드
		// 0으로 바꾸는건 deleteMember(int mem_num)에서 실행
		public void updateAuth(int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				// 커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "update xmember set auth = 1 where mem_num=?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				// 자원 정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		public void updateAuth(int auth,String id) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				
				sql = "update xmember set auth = ? where id=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1,auth);
				pstmt.setString(2,id);
				
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
}

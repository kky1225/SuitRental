package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

// 2021-09-06 서준화
public class MemberDAO {
	// 싱글톤 패턴
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private MemberDAO() {}
	
	// 회원가입
	public void insertMember(MemberVO member) throws Exception{
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
				
				
			sql = "select xmember_seq.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
				
			if(rs.next()) {
				num = rs.getInt(1);	
			}
				
				
			sql = "insert into xmember (mem_num, id,auth) values (?,?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, num);	
			pstmt2.setString(2,member.getId());
			pstmt2.setInt(3, member.getAuth());
			pstmt2.executeUpdate();
				
			// zmember_detail  insert
			sql = "insert into xmember_detail(mem_num,name,passwd,phone,email,zipcode,address1,address2,gender) values (?,?,?,?,?,?,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, num);
			pstmt3.setString(2, member.getName());
			pstmt3.setString(3, member.getPasswd());
			pstmt3.setString(4, member.getPhone());
			pstmt3.setString(5, member.getEmail());
			pstmt3.setString(6, member.getZipcode());
			pstmt3.setString(7, member.getAddress1());
			pstmt3.setString(8, member.getAddress2());
			pstmt3.setString(9, member.getGender());
			pstmt3.executeUpdate();
				
			conn.commit();
		}catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
			
		} 
		
		// ID 중복 확인 및 로그인 처리
		public MemberVO checkMember(String id) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
				
			try {
				conn = DBUtil.getConnection();			
				
				sql = "select * from xmember m left outer join xmember_detail d "
						+ "on m.mem_num = d.mem_num where m.id=?";
				
				pstmt = conn.prepareStatement(sql);
					
				pstmt.setString(1, id);
					
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setId(rs.getString("id"));
					member.setAuth(rs.getInt("auth"));
					member.setPasswd(rs.getString("passwd"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return member;
		}
			
		// 회원 상세 정보
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
		// 수정 가능 정보 : name,phone,email,zipcode,address1,address2,modify_date=SYSDATE
		// PK : mem_num
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			// 수정 가능 정보 : name,phone,email,zipcode,address1,address2,modify_date=SYSDATE
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
			// 자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 회원 비밀번호 수정
	public void updatePassword(String passwd, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "update xmember_detail set passwd=? where mem_num=?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, passwd);	
			pstmt.setInt(2, mem_num);	
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 회원 탈퇴(auth를 0으로)
	public void deleteMember(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			conn.setAutoCommit(false);
			
			sql = "update xmember set auth=0 where mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.executeUpdate();
			
			sql = "delete from xmember_detail where mem_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, mem_num);
			pstmt2.executeUpdate();
			
			conn.commit();
		}catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 대여 목록
	
	// 대여 취소
	
	// 대여 변경
	
	// auth를 1로 바꿈 대여금지 회원으로
	// 0으로 바꾸는건 deleteMember(int mem_num)에서 실행
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






























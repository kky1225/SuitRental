package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.member.vo.OrderVO;
import kr.util.DBUtil;

public class OrderDAO {

	private static OrderDAO instance = new OrderDAO();
	
	public static OrderDAO getInstance() {
		return instance;
	}
	
	private OrderDAO() {}
	
	// 주문 개수 구하기
	public int getOrderCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "select count(*) from rental r join xmember m on r.mem_num = m.mem_num";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
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
	
	// 대여 목록
	public List<OrderVO> getOrderList(MemberVO member,int start,int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderVO> list = null;
		String sql = null;
			
		try {
			conn = DBUtil.getConnection();
				
			sql = "select * from (select a.*, rownum rnum from "
					+ "(select * from rental r join suit s on r.x_code = s.x_code "
					+ "where r.mem_num=? order by r.rent_num desc)a) where rnum >= ? and rnum <=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1,member.getMem_num());
			pstmt.setInt(2,start);
			pstmt.setInt(3,end);
				
			// SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<OrderVO>();
			while(rs.next()) {
				OrderVO item = new OrderVO();
				item.setRent_num(rs.getInt("rent_num"));
				item.setX_code(rs.getInt("x_code"));
				item.setX_name(rs.getString("x_name"));
				item.setMem_num(rs.getInt("mem_num"));
				item.setRental_date(rs.getString("rental_date"));
				item.setReturn_date(rs.getString("return_date"));
				item.setRental_type(rs.getString("rental_type"));
				item.setReturn_type(rs.getString("return_type"));
				
				list.add(item);
			}
				
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
		
	// 대여 상세 내역	
	public OrderVO getOrder(int rent_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO order = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "select * from rental r join suit s on r.x_code = s.x_code where r.rent_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rent_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				order = new OrderVO();
				order.setRent_num(rs.getInt("rent_num"));
				order.setX_code(rs.getInt("x_code"));
				order.setX_name(rs.getString("x_name"));
				order.setMem_num(rs.getInt("mem_num"));
				order.setRental_date(rs.getString("rental_date"));
				order.setReturn_date(rs.getString("return_date"));
				order.setReturn_type(rs.getString("return_type"));
				order.setRental_type(rs.getString("rental_type"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return order;
	}
		
	// 대여 취소
	public void deleteOrder(OrderVO order) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			// rental_detail 삭제
			sql = "delete from rental_detail where rent_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,order.getRent_num());
			pstmt.executeUpdate();
			
			// rental 삭제
			sql = "delete from rental where rent_num=?";
			
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1,order.getRent_num());
			pstmt2.executeUpdate();
			
			// SUIT 재고 + 1 구매수 -1
			sql = "update suit set x_stock = x_stock+1, x_purchase = x_purchase-1 where x_code=?";
			
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1,order.getX_code());
			pstmt3.executeUpdate();
			
			conn.commit();
			
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 대여 변경	
	public void updateOrder(OrderVO order) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "update rental set rental_date=?, return_date=?, rental_type=?, return_type=? where rent_num=?";
			
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1,order.getRental_date());
			pstmt.setString(2,order.getReturn_date());
			pstmt.setString(3,order.getRental_type());
			pstmt.setString(4,order.getReturn_type());
			pstmt.setInt(5,order.getRent_num());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}	
		
		
}


































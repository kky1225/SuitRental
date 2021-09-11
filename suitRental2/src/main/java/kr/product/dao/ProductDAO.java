package kr.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;


import kr.productdetail.vo.ProductDetailVO;
import kr.util.DBUtil;

public class ProductDAO {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql=null;
		
	private static ProductDAO instance=new ProductDAO();
	
	private ProductDAO() {
		
	}//기본 생성자
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	public void product()throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn=DBUtil.getConnection();
			
		
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, null);
		}
	};
	
	
	
	
	
	public int getProductCount()throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		int count=0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn=DBUtil.getConnection();
			//SQL문 작성
			sql="SELECT COUNT (*) FROM suit";
		
			//PreparedStatement 객체 생성
			pstmt=conn.prepareStatement(sql);
			//SQL문 실행하고 결과행을 ResultSet에 담음
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
			
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
			
		}
		return count;
	}
	
	//게시물을 보여주는 메서드
	public List<ProductDetailVO> getProductList(int page,int rowsize) throws Exception {
		List<ProductDetailVO> list = new ArrayList<ProductDetailVO>();
		
		//해당 페이지에서의 시작번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		//해당 페이지에서의 마지막번호
		int endNo = (page * rowsize);
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn=DBUtil.getConnection();
			//SQL문 작성
			sql="select * from (select row_number() "
				+ "over(order by x_code desc) rnum, "
				+ "b.* from suit b) "
				+ "where rnum >= ? and rnum <=?";
		
			//PreparedStatement 객체 생성
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			//SQL문 실행하고 결과행을 ResultSet에 담음
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDetailVO board = new ProductDetailVO();
				board.setX_file(rs.getString("x_file"));
				board.setX_name(rs.getString("x_name"));
				board.setX_code(rs.getInt("x_code"));
				
				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
			
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	
	//글 상세
		public ProductDetailVO getBoard(int x_code)throws Exception{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ProductDetailVO board=null;
			String sql=null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn=DBUtil.getConnection();
				//SQL문 작성
				sql="SELECT * FROM suit WHERE x_code=?";
				//PreparedStatement 객체 생성
				pstmt=conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, x_code);
				//SQL문을 실행해서 결과행을 ResultSet에 담음
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					board=new ProductDetailVO();
					board.setX_name(rs.getString("x_name"));
					board.setX_file(rs.getString("x_file"));
					board.setX_price(rs.getInt("x_price"));
					board.setX_stock(rs.getInt("x_stock"));
					board.setX_gender(rs.getString("x_gender"));
					board.setX_size(rs.getString("x_size"));
					board.setX_brand(rs.getString("x_brand"));
					board.setX_rental_count(rs.getInt("x_rental_count"));
					board.setX_hit(rs.getInt("x_rental_count"));
					board.setX_like(rs.getInt("x_like"));
					board.setX_reg_date(rs.getDate("x_reg_date"));
					board.setX_purchase(rs.getInt("x_purchase"));
					board.setX_type(rs.getString("x_type"));
					board.setX_contents(rs.getString("x_contents"));
					
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return board;
			
		}
		
		
		//상품 등록 모듈
		public int registerProduct(ProductDetailVO dto) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;		
			int result=0;
			
			try {
					conn=DBUtil.getConnection();
					
					sql="INSERT into suit VALUES(suit_seq.nextval,?,?,?,?,?,default,?,default,default,default,default,?,?,?)";
					
					pstmt=conn.prepareStatement(sql);
					
					
					pstmt.setString(1, dto.getX_name());
					pstmt.setInt(2, dto.getX_price());
					pstmt.setInt(3, dto.getX_stock());
					pstmt.setString(4, dto.getX_size());
					pstmt.setString(5, dto.getX_brand());
					pstmt.setString(6, dto.getX_gender());
					pstmt.setString(7, dto.getX_type());
					pstmt.setString(8, dto.getX_contents());
					pstmt.setString(9, dto.getX_file());
					
					result=pstmt.executeUpdate();
					
			}catch(SQLException e) {
				e.printStackTrace();
				
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return result;
			}
		//registerProduct() -메서드 처리 (ProductWrite.jsp에 있음)
		
	
		
		//조회수 증가
		public void updateReadcount(int x_code)throws Exception{
			Connection conn=null;
			PreparedStatement pstmt=null;
			String sql=null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn=DBUtil.getConnection();
				
				//sql문 작성
				sql="UPDATE suit SET x_hit=x_hit+1 WHERE x_code=?";
				
				//PreparedStatement객체 생성
				pstmt=conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(1, x_code);
				
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, null);
			}
}
}


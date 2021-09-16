package kr.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	
	
	public int getProductCount(String keyfield, String keyword)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		String sub_sql = null;
		int count=0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn=DBUtil.getConnection();
			//SQL문 작성
			if(keyword == null || keyword.equals("")) {
			sql="SELECT COUNT (*) FROM suit";
			
			//PreparedStatement 객체 생성
			pstmt=conn.prepareStatement(sql);
			}else {
				if(keyfield.equals("1")) sub_sql = "x_name LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "x_brand LIKE ?";
				
				sql = "SELECT COUNT(*) FROM suit WHERE "+ sub_sql;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");
			}
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
	
	public List<ProductDetailVO> getProductList(int start, int end, String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		List<ProductDetailVO> list = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn=DBUtil.getConnection();
			
			if(keyword == null || keyword.equals("")) {
				
			sql = "SELECT * FROM (SELECT a.*, rownum AS rnum FROM (SELECT * FROM suit ORDER BY x_like DESC)a) WHERE rnum >=? AND rnum <= ?";
			
			//PreparedStatement 객체 생성
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
		}else {
			if(keyfield.equals("1")) sub_sql = "x_name LIKE ?";
			else if(keyfield.equals("2")) sub_sql = "x_brand LIKE ?";
			
			sql = "SELECT * FROM (SELECT a.*, rownum AS rnum FROM (SELECT * FROM suit WHERE " + sub_sql + " ORDER BY  x_like DESC)a) WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");

			
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
	
		}
				
			//SQL문 실행하고 결과행을 ResultSet에 담음
			rs=pstmt.executeQuery();
			
			list = new ArrayList<ProductDetailVO>();
			
			while(rs.next()) {
				ProductDetailVO board = new ProductDetailVO();
				board.setX_file(rs.getString("x_file"));
				board.setX_name(rs.getString("x_name"));
				board.setX_code(rs.getInt("x_code"));
				board.setX_brand(rs.getString("x_brand"));
				board.setX_price(rs.getInt("x_price"));
				board.setX_like(rs.getInt("x_like"));
				
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
	
	
	public ProductDetailVO getBestProduct() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ProductDetailVO productDetailVO = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn=DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum AS rnum FROM (SELECT * FROM suit ORDER BY x_like DESC)a) WHERE rnum = 1";
		
			//PreparedStatement 객체 생성
			pstmt=conn.prepareStatement(sql);
			//SQL문 실행하고 결과행을 ResultSet에 담음
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				productDetailVO = new ProductDetailVO();
				productDetailVO.setX_file(rs.getString("x_file"));
				productDetailVO.setX_name(rs.getString("x_name"));
				productDetailVO.setX_code(rs.getInt("x_code"));
				productDetailVO.setX_brand(rs.getString("x_brand"));
				productDetailVO.setX_price(rs.getInt("x_price"));
			}
		}catch(Exception e) {
			throw new Exception(e);
			
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return productDetailVO;
	}
	
	public List<ProductDetailVO> getBestList(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<ProductDetailVO> list = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn=DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum AS rnum FROM (SELECT * FROM suit ORDER BY x_like DESC)a) WHERE rnum >=? AND rnum <= ?";
		
			//PreparedStatement 객체 생성
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//SQL문 실행하고 결과행을 ResultSet에 담음
			rs=pstmt.executeQuery();
			
			list = new ArrayList<ProductDetailVO>();
			
			while(rs.next()) {
				ProductDetailVO board = new ProductDetailVO();
				board.setX_file(rs.getString("x_file"));
				board.setX_name(rs.getString("x_name"));
				board.setX_code(rs.getInt("x_code"));
				board.setX_brand(rs.getString("x_brand"));
				board.setX_price(rs.getInt("x_price"));
				
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
					board.setX_code(rs.getInt("x_code"));
					board.setX_name(rs.getString("x_name"));
					board.setX_file(rs.getString("x_file"));
					board.setX_price(rs.getInt("x_price"));
					board.setX_stock(rs.getInt("x_stock"));
					board.setX_gender(rs.getString("x_gender"));
					board.setX_size(rs.getString("x_size"));
					board.setX_brand(rs.getString("x_brand"));
					board.setX_rental_count(rs.getInt("x_rental_count"));
					board.setX_hit(rs.getInt("x_hit"));
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
		
	public void updateProduct(ProductDetailVO productDetailVO, int check) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn=DBUtil.getConnection();
			
			if(check == 1) {
				sql="UPDATE suit SET x_name = ?, x_price = ?, x_stock = ?, x_size = ?, x_brand = ?, x_gender = ?, x_type = ?, x_contents = ? WHERE x_code = ?";
				
				pstmt=conn.prepareStatement(sql);

				pstmt.setString(1, productDetailVO.getX_name());
				pstmt.setInt(2, productDetailVO.getX_price());
				pstmt.setInt(3, productDetailVO.getX_stock());
				pstmt.setString(4, productDetailVO.getX_size());
				pstmt.setString(5, productDetailVO.getX_brand());
				pstmt.setString(6, productDetailVO.getX_gender());
				pstmt.setString(7, productDetailVO.getX_type());
				pstmt.setString(8, productDetailVO.getX_contents());
				pstmt.setInt(9, productDetailVO.getX_code());
						
				pstmt.executeUpdate();
			}else{
				sql="UPDATE suit SET x_name = ?, x_price = ?, x_stock = ?, x_size = ?, x_brand = ?, x_gender = ?, x_type = ?, x_contents = ?, x_file = ? WHERE x_code = ?";
				
				pstmt=conn.prepareStatement(sql);

				pstmt.setString(1, productDetailVO.getX_name());
				pstmt.setInt(2, productDetailVO.getX_price());
				pstmt.setInt(3, productDetailVO.getX_stock());
				pstmt.setString(4, productDetailVO.getX_size());
				pstmt.setString(5, productDetailVO.getX_brand());
				pstmt.setString(6, productDetailVO.getX_gender());
				pstmt.setString(7, productDetailVO.getX_type());
				pstmt.setString(8, productDetailVO.getX_contents());
				pstmt.setString(9, productDetailVO.getX_file());
				pstmt.setInt(10, productDetailVO.getX_code());
						
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}			

	}
		
	
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
		
	public void deleteProduct(int x_code) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;
		try {
			conn= DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			sql = "DELETE FROM xreview WHERE x_code = ?";
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, x_code);
			
			pstmt1.executeQuery();
			
			sql = "DELETE FROM xlikey WHERE x_code = ?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, x_code);
			
			pstmt2.executeQuery();
			
			sql = "DELETE FROM suit WHERE x_code = ?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, x_code);
			
			pstmt3.executeQuery();
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
					
	}
}
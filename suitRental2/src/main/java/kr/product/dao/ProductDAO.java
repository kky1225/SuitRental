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

import kr.product.vo.ProductVO;
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
	public List<ProductVO> getProductList(int page,int rowsize) throws Exception {
		List<ProductVO> list = new ArrayList<ProductVO>();
		
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
				ProductVO dto = new ProductVO();
				dto.setX_file(rs.getString("x_file"));
				dto.setX_name(rs.getString("x_name"));
				dto.setX_code(rs.getInt("x_code"));
				
				list.add(dto);
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
					board.setX_name(rs.getString("s_name"));
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
		public int registerProduct(MultipartRequest multi) throws SQLException{
				Connection conn=null;
				PreparedStatement pstmt=null;
				String sql=null;
				
			try {
				conn=getConnection();
				sql="INSERT into suit VALUES(suit_seq.nextval,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				
				String x_name=multi.getParameter("x_name");
				String x_brand=multi.getParameter("x_brand");
				String x_file=multi.getFilesystemName("x_file");
				String x_gender=multi.getParameter("x_gender");
				String x_size=multi.getParameter("x_size");
				String x_price=multi.getParameter("price");
				String x_contents=multi.getParameter("x_contents");
				
				
				pstmt.setString(1,x_name);
				pstmt.setString(2, x_brand);
				pstmt.setString(3, x_file);
				pstmt.setString(4, x_gender);
				pstmt.setString(5, x_size);
				pstmt.setString(6, x_price);
				pstmt.setString(7, x_contents);
				
				int n=pstmt.executeUpdate();
				return n;
				
			}finally {
				if(pstmt !=null)pstmt.close();
				if(conn !=null)conn.close();
			}
		}//registerProduct() -메서드 처리 (ProductWrite.jsp에 있음)
		
		//Connection pool에서 connection확보
		private Connection getConnection() {
			Context ctx=null;
			DataSource ds=null;
			Connection conn=null;
			
			try {
				ctx=new InitialContext();
				ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
				conn=ds.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return conn;
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
}

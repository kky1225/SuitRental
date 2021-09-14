package kr.board.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardQnaVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class BoardQnaDAO {
	// 싱글턴 패턴
	private static BoardQnaDAO instance = new BoardQnaDAO();
	
	public static BoardQnaDAO getInstance() {
		return instance;
	}
	
	private BoardQnaDAO() {}
	
	// 글 등록
	public void insertBoardQna(BoardQnaVO boardQna) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			// 질문 작성
			sql = "INSERT INTO xboard_qna (qna_num, title, q_content, filename, ip, mem_num) VALUES (xboard_qna_seq.nextval, ?,?,?,?,?)";
			// PreparedStatement객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, boardQna.getTitle());
			pstmt.setString(2, boardQna.getQ_content());
			pstmt.setString(3, boardQna.getFilename());
			pstmt.setString(4, boardQna.getIp());
			pstmt.setInt(5, boardQna.getMem_num());
			
			// SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			// 자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 답변 글 등록
	public void answerBoardQna(BoardQnaVO boardQna) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			//UPDATE 테이블명 SET a_content=? WHERE board_num=? 
			sql = "UPDATE xboard_qna SET a_content=? WHERE qna_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardQna.getA_content());
			pstmt.setInt(2, boardQna.getQna_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 총 레코드 수 
	public int getBoardQnaCount(String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword == null || "".equals(keyword)) {
				// 전체글 갯수
				sql = "SELECT COUNT(*) FROM xboard_qna b JOIN xmember m ON b.mem_num = m.mem_num";
				pstmt = conn.prepareStatement(sql);
			}else {
				// 검색글 갯수
				if(keyfield.equals("1")) sub_sql = "b.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "m.id = ?";
				else if(keyfield.equals("3")) sub_sql = "b.q_content LIKE ?";
				
				sql = "SELECT COUNT(*) FROM xboard_qna b JOIN xmember m ON b.mem_num = m.mem_num WHERE " + sub_sql;
				pstmt = conn.prepareStatement(sql);
				if(keyfield.equals("2")) {			// 조건 명시
					pstmt.setString(1, keyword);
				}else {					
					pstmt.setString(1, "%"+keyword+"%");
				}
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			// 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	// 글 목록
	public List<BoardQnaVO> getListBoardQna(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardQnaVO> list = null;
		String sql = null;
		String sub_sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword == null || "".equals(keyword)) {
				// 전체글 보기
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ "(SELECT * FROM xboard_qna b JOIN xmember m ON b.mem_num = m.mem_num "
						+ "ORDER BY b.qna_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}else {
				// 검색글 보기
				if(keyfield.equals("1")) sub_sql = "b.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "m.id =?";
				else if(keyfield.equals("3")) sub_sql = "b.q_content LIKE ?";
				
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ "(SELECT * FROM xboard_qna b JOIN xmember m ON b.mem_num = m.mem_num WHERE " + sub_sql
						+ " ORDER BY b.qna_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				if(keyfield.equals("2")) {			// 조건 명시
					pstmt.setString(1, keyword);
				}else {
					pstmt.setString(1, "%"+keyword+"%");
				}
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardQnaVO>();
			while(rs.next()) {
				BoardQnaVO board = new BoardQnaVO();
				board.setQna_num(rs.getInt("qna_num"));
				board.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				board.setQ_content(rs.getString("q_content"));
				board.setA_content(rs.getString("a_content"));
				board.setHit(rs.getInt("hit"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setans_date(rs.getDate("ans_date"));
				board.setFilename(rs.getString("filename"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setId(rs.getString("id"));
				board.setIp(rs.getString("ip"));
				
				list.add(board);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			// 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	// 글 상세
	public BoardQnaVO getBoardQna(int qna_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardQnaVO boardQna = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM xboard_qna b JOIN xmember m ON b.mem_num=m.mem_num WHERE b.qna_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardQna = new BoardQnaVO();
				boardQna.setQna_num(rs.getInt("qna_num"));
				boardQna.setTitle(rs.getString("title"));
				boardQna.setA_content(rs.getString("a_content"));
				boardQna.setQ_content(rs.getString("q_content"));
				boardQna.setHit(rs.getInt("hit"));
				boardQna.setReg_date(rs.getDate("reg_date"));
				boardQna.setFilename(rs.getString("filename"));
				boardQna.setMem_num(rs.getInt("mem_num"));
				boardQna.setId(rs.getString("id"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return boardQna;
	}

	// 답변글 상세
	public BoardQnaVO getAnswerBoard(int qna_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardQnaVO answerBoard = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM xboard_qna b JOIN xmember m ON b.mem_num=m.mem_num WHERE b.qna_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				answerBoard = new BoardQnaVO();
				answerBoard.setQna_num(rs.getInt("qna_num"));
				answerBoard.setTitle(rs.getString("title"));
				answerBoard.setQ_content(rs.getString("q_content"));
				answerBoard.setA_content(rs.getString("a_content"));
				answerBoard.setHit(rs.getInt("hit"));
				answerBoard.setFilename(rs.getString("filename"));
				answerBoard.setReg_date(rs.getDate("reg_date"));
				answerBoard.setMem_num(rs.getInt("mem_num"));
				answerBoard.setId(rs.getString("id"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return answerBoard;
	}
	
	// 조회수 증가
	public void updateReadcount(int qna_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "UPDATE xboard_qna SET hit=hit+1 WHERE qna_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 글 수정
	public void updateBoard(BoardQnaVO boardQna) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			if(boardQna.getFilename() != null) {
				// 새로 업로드되는 파일이 있을 때
				sql = "UPDATE xboard_qna SET title=?, q_content=?, a_content=?, filename=?, ip=? WHERE qna_num=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardQna.getTitle());
				pstmt.setString(2, boardQna.getQ_content());
				pstmt.setString(3, boardQna.getA_content());
				pstmt.setString(4, boardQna.getFilename());
				pstmt.setString(5, boardQna.getIp());
				pstmt.setInt(6, boardQna.getQna_num());
			}else {
				// 업로드되는 파일이 없을 때
				sql = "UPDATE xboard_qna SET title=?, q_content=?, a_content=?, ip=? WHERE qna_num=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardQna.getTitle());
				pstmt.setString(2, boardQna.getQ_content());
				pstmt.setString(3, boardQna.getA_content());
				pstmt.setString(4, boardQna.getIp());
				pstmt.setInt(5, boardQna.getQna_num());
			}
			
			// SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 글 삭제
	public void deleteBoardQna(int qna_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "DELETE FROM xboard_qna WHERE qna_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 답변글 삭제
	public void deleteAnswerBoard(int qna_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "DELETE FROM xboard_qna WHERE qna_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}

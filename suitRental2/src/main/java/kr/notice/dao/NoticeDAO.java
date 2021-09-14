package kr.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.notice.vo.NoticeReplyVO;
import kr.notice.vo.NoticeVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;

public class NoticeDAO {
	private static NoticeDAO instance = new NoticeDAO();
	public static NoticeDAO getInstance() {
		return instance;
	}
	private NoticeDAO() {};
	
	//글 등록
	public void insertNotice(NoticeVO notice)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn=DBUtil.getConnection();
			sql="INSERT INTO xnotice (notice_num, title, content, filename, ip, mem_num) VALUES(xnotice_seq.nextval,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setString(3, notice.getFilename());
			pstmt.setString(4, notice.getIp());
			pstmt.setInt(5, notice.getMem_num());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 목록
	public List<NoticeVO> getNoticeList(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = null;
		ResultSet rs= null;
		List<NoticeVO> list = null;
		
		try {
			conn=DBUtil.getConnection();
			if(keyword==null || "".equals(keyword)) {
				//전체 글 보기
				sql = "SELECT * FROM(SELECT a.*, rownum rnum FROM (SELECT * FROM xnotice n JOIN xmember m ON n.mem_num=m.mem_num ORDER BY n.notice_num DESC)a) WHERE rnum>=? AND rnum<=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}else {
				//검색 글 보기
				if(keyfield.equals("1")) {
					sub_sql="n.title LIKE ?";
				}else if(keyfield.equals("2")) {
					sub_sql="m.id=?";
				}else if(keyfield.equals("3")) {
					sub_sql="n.content LIKE ?";
				}
				
				sql = "SELECT * FROM(SELECT a.*, rownum rnum FROM (SELECT * FROM xnotice n JOIN xmember m ON n.mem_num=m.mem_num WHERE "+sub_sql+" ORDER BY n.notice_num DESC)a) WHERE rnum>=? AND rnum<=?";
				pstmt=conn.prepareStatement(sql);
				if(keyfield.equals("2")) {
					pstmt.setString(1, keyword);
				}else {
					pstmt.setString(1, "%"+keyword+"%");
				}
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
			}
			
			rs = pstmt.executeQuery();
			list = new ArrayList<NoticeVO>();
			while(rs.next()) {
				NoticeVO notice = new NoticeVO();
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setFilename(rs.getString("filename"));
				notice.setHit(rs.getInt("hit"));
				notice.setIp(rs.getString("ip"));
				notice.setReg_date(rs.getDate("reg_date"));
				notice.setModify_date(rs.getDate("modify_date"));
				notice.setMem_num(rs.getInt("mem_num"));
				notice.setMem_id(rs.getString("id"));
				
				list.add(notice);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//총 레코드 수
	public int getNoticeCount(String keyfield, String keyword)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = null;
		ResultSet rs= null;
		int count = 0;
		
		try {
			conn=DBUtil.getConnection();
			if(keyword == null || "".equals(keyword)) {
				//전체 글 갯수
				sql = "SELECT count(*) FROM xnotice n JOIN xmember m ON n.mem_num = m.mem_num";		
				pstmt = conn.prepareStatement(sql);
			}else {
				//검색 글 갯수
				if(keyfield.equals("1")) {
					sub_sql = "n.title LIKE ?";
				}else if(keyfield.equals("2")) {
					sub_sql = "m.id = ?";
				}else if(keyfield.equals("3")) {
					sub_sql="n.content LIKE ?";
				}
				sql = "SELECT COUNT(*) FROM xnotice n JOIN xmember m ON n.mem_num = m.mem_num WHERE " + sub_sql;
				pstmt=conn.prepareStatement(sql);
				if(keyfield.equals("2")) {
					pstmt.setString(1, keyword);
				}else {
					pstmt.setString(1, "%"+keyword+"%");
				}
			}
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	//조회수 증가
	public void	updateReadCount(int notice_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn=DBUtil.getConnection();
			sql="UPDATE xnotice SET hit=hit+1 WHERE notice_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 상세페이지
	public NoticeVO getNotice(int notice_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs= null;
		NoticeVO notice = null;
		
		try {
			conn = DBUtil.getConnection();
			sql="SELECT * FROM xnotice n JOIN xmember m ON n.mem_num=m.mem_num WHERE n.notice_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new NoticeVO();
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setFilename(rs.getString("filename"));
				notice.setHit(rs.getInt("hit"));
				notice.setIp(rs.getString("ip"));
				notice.setReg_date(rs.getDate("reg_date"));
				notice.setModify_date(rs.getDate("modify_date"));
				notice.setMem_num(rs.getInt("mem_num"));
				notice.setMem_id(rs.getString("id"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return notice;
	}
	//글 수정
	public void updateNotice(NoticeVO notice, int check)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn=DBUtil.getConnection();

			if(check == 1) {
				if(notice.getFilename() == null) {
					sql = "UPDATE xnotice SET title = ?, content = ?, ip = ?, modify_date = SYSDATE WHERE notice_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, notice.getTitle());
					pstmt.setString(2, notice.getContent());
					pstmt.setString(3, notice.getIp());
					pstmt.setInt(4, notice.getNotice_num());
				}else {
					sql = "UPDATE xnotice SET title = ?, content = ?, filename = ?, ip = ?, modify_date = SYSDATE WHERE notice_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, notice.getTitle());
					pstmt.setString(2, notice.getContent());
					pstmt.setString(3, notice.getFilename());
					pstmt.setString(4, notice.getIp());
					pstmt.setInt(5, notice.getNotice_num());
				}
			}else if(check == 0) {
				sql = "UPDATE xnotice SET title = ?, content = ?, filename = null, ip = ?, modify_date = SYSDATE WHERE notice_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, notice.getTitle());
				pstmt.setString(2, notice.getContent());
				pstmt.setString(3, notice.getIp());
				pstmt.setInt(4, notice.getNotice_num());
			}
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 삭제
	public void deleteNotice(int notice_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn=DBUtil.getConnection();
			//auto commit 해제
			conn.setAutoCommit(false);
			
			sql="DELETE FROM xnotice_reply WHERE notice_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			pstmt.executeUpdate();
			
			sql="DELETE FROM xnotice WHERE notice_num=?";
			pstmt2=conn.prepareStatement(sql);
			pstmt2.setInt(1, notice_num);
			pstmt2.executeUpdate();
			
			//모든 sql문의 실행이 성공
			conn.commit();
			
		}catch(Exception e) {
			//sql문이 하나라도 실패하면 롤백
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, null);
			DBUtil.executeClose(null, pstmt2, conn);
		}
	}
	//댓글 등록
	public void insertNoticeReply(NoticeReplyVO notice)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql="INSERT INTO xnotice_reply (re_num,re_content,re_ip,mem_num,notice_num) VALUES(notice_reply_seq.nextval,?,?,?,?) ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, notice.getRe_content());
			pstmt.setString(2, notice.getRe_ip());
			pstmt.setInt(3, notice.getMem_num());
			pstmt.setInt(4, notice.getNotice_num());
			
			pstmt.executeUpdate();
		
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 수
	public int getReplyCount(int notice_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT count(*) FROM xnotice_reply r JOIN xmember m ON r.mem_num=m.mem_num WHERE notice_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	//댓글 목록
	public List<NoticeReplyVO> getNoticeReplyList(int start, int end, int notice_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		List<NoticeReplyVO> list = null;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM(SELECT a.*, rownum rnum FROM(SELECT r.re_num, r.re_content, TO_CHAR(r.re_date,'YYYY-MM-DD HH24:MI:SS') re_date, TO_CHAR(r.re_modifydate,'YYYY-MM-DD HH24:MI:SS') re_modifydate, r.mem_num, r.notice_num, m.id FROM xnotice_reply r JOIN xmember m ON r.mem_num=m.mem_num WHERE r.notice_num=? ORDER BY r.re_num DESC)a)WHERE rnum>=? AND rnum<=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			list = new ArrayList<NoticeReplyVO>();
			rs=pstmt.executeQuery();
			while(rs.next()) {
				NoticeReplyVO reply = new NoticeReplyVO();
				reply.setRe_num(rs.getInt("re_num"));
				reply.setRe_content(rs.getString("re_content"));
				reply.setMem_num(rs.getInt("mem_num"));
				reply.setNotice_num(rs.getInt("notice_num"));
				reply.setId(rs.getString("id"));
				reply.setRe_date(DurationFromNow.getTimeDiffLabel(rs.getString("re_date")));
				reply.setRe_modifydate(DurationFromNow.getTimeDiffLabel(rs.getString("re_modifydate")));
				
				list.add(reply);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
		
	}
	
	//댓글 수정
	public void updateNoticeReply(NoticeReplyVO reply)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn=DBUtil.getConnection();
			sql="UPDATE xnotice_reply SET re_content=?,re_ip=?,re_modifydate=SYSDATE WHERE re_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, reply.getRe_content());
			pstmt.setString(2, reply.getRe_ip());
			pstmt.setInt(3, reply.getRe_num());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 삭제
	public void deleteNoticeReply(int re_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn=DBUtil.getConnection();
			sql="DELETE FROM xnotice_reply WHERE re_num=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, re_num);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}

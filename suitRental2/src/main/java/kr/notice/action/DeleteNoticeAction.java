package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;

public class DeleteNoticeAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 안됨.
			return "redirect:/member/loginForm.do";
		}
		//로그인 됨.
		
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		NoticeDAO noticeDao = NoticeDAO.getInstance();
		
		NoticeVO notice = noticeDao.getNotice(notice_num);
		if(user_num !=notice.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		noticeDao.deleteNotice(notice_num);					
		//파일삭제
		FileUtil.removeFile(request, notice.getFilename());
		
		
		
		return "/WEB-INF/views/notice/deleteNotice.jsp";
	}
}

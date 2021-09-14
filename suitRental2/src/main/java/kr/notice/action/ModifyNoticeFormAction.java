package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;

public class ModifyNoticeFormAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {	//로그인 안됨
			return "redirect:/member/loginForm.do";
		}
		//로그인 됨.
		
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeVO notice = dao.getNotice(notice_num);
		
		//작성자와 로그인한 사용자가 같은지 체크
		if(user_num!=notice.getMem_num()) {//다른경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		//로그인이 되어있고 로그인한 회원번호와 작성자 회원번호 일치
		//request에 데이터 저장
		request.setAttribute("notice", notice);
		
		
		return "/WEB-INF/views/notice/modifyNoticeForm.jsp";
	} 
}

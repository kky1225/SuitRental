package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class DeleteNoticeFormAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//�α��� Ȯ��
		HttpSession session=request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//�α��� �ȵ�.
			return "redirect:/member/loginForm.do";
		}
		//�α��� ��.
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		request.setAttribute("notice_num", notice_num);
		
		return "/WEB-INF/views/notice/deleteNoticeForm.jsp";
	}
}

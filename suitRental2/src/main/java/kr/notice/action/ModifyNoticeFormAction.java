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
		//�α��� Ȯ��
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {	//�α��� �ȵ�
			return "redirect:/member/loginForm.do";
		}
		//�α��� ��.
		
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeVO notice = dao.getNotice(notice_num);
		
		//�ۼ��ڿ� �α����� ����ڰ� ������ üũ
		if(user_num!=notice.getMem_num()) {//�ٸ����
			return "/WEB-INF/views/common/notice.jsp";
		}
		//�α����� �Ǿ��ְ� �α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ ��ġ
		//request�� ������ ����
		request.setAttribute("notice", notice);
		
		
		return "/WEB-INF/views/notice/modifyNoticeForm.jsp";
	} 
}

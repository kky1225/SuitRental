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
		
		//�α���Ȯ��
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//�α��� �ȵ�.
			return "redirect:/member/loginForm.do";
		}
		//�α��� ��.
		
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		NoticeDAO noticeDao = NoticeDAO.getInstance();
		
		NoticeVO notice = noticeDao.getNotice(notice_num);
		if(user_num !=notice.getMem_num()) {
			//�α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ����ġ
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//�α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ��ġ
		noticeDao.deleteNotice(notice_num);					
		//���ϻ���
		FileUtil.removeFile(request, notice.getFilename());
		
		
		
		return "/WEB-INF/views/notice/deleteNotice.jsp";
	}
}

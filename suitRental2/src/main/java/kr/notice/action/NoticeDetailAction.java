package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.StringUtil;

public class NoticeDetailAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		//��ȸ��
		NoticeDAO dao = NoticeDAO.getInstance();
		dao.updateReadCount(notice_num);
		//������ ��������
		NoticeVO notice = dao.getNotice(notice_num);
		//������ �ٹٲ� ���� html ������
		notice.setContent(StringUtil.useBrNoHtml(notice.getContent()));
		//���� html������
		notice.setTitle(StringUtil.useNoHtml(notice.getTitle()));
		
		request.setAttribute("notice", notice);
		
		return "/WEB-INF/views/notice/noticeDetail.jsp";
	}
}

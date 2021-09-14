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
		
		//조회수
		NoticeDAO dao = NoticeDAO.getInstance();
		dao.updateReadCount(notice_num);
		//상세정보 가져오기
		NoticeVO notice = dao.getNotice(notice_num);
		//본문에 줄바꿈 적용 html 미적용
		notice.setContent(StringUtil.useBrNoHtml(notice.getContent()));
		//제목에 html미적용
		notice.setTitle(StringUtil.useNoHtml(notice.getTitle()));
		
		request.setAttribute("notice", notice);
		
		return "/WEB-INF/views/notice/noticeDetail.jsp";
	}
}

package kr.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.PagingUtil;

public class NoticeListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		String keyfield=request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
	
		if(keyfield == null) {
			keyfield="";
		}
		if(keyword == null) {
			keyword="";
		}
		//count 구하기
		NoticeDAO dao = NoticeDAO.getInstance();
		int count = dao.getNoticeCount(keyfield,keyword);
		//page처리
		PagingUtil page = new PagingUtil(keyfield,keyword,Integer.parseInt(pageNum), count, 10, 10, "noticeList.do");
		//list구하기
		List<NoticeVO> list = null;
		if(count>0) {
			list = dao.getNoticeList(page.getStartCount(), page.getEndCount(),keyfield,keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/notice/noticeList.jsp";
	}
}

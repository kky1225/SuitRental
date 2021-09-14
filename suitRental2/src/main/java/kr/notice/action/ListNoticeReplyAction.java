package kr.notice.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeReplyVO;
import kr.util.PagingUtil;

public class ListNoticeReplyAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		int rowCount = 10;
		
		//count
		NoticeDAO dao = NoticeDAO.getInstance();
		int count = dao.getReplyCount(notice_num);
		
		//page
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, rowCount, 10, null);
		//list
		List<NoticeReplyVO> list = null;
		if(count>0) {
			list=dao.getNoticeReplyList(page.getStartCount(), page.getEndCount(), notice_num);
		}else {
			list=Collections.emptyList();
		}
		
		Map<String,Object> mapAjax = new HashMap<String, Object>(); 
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}

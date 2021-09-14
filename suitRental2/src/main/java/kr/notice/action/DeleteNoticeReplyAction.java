package kr.notice.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;

public class DeleteNoticeReplyAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//데이터 전송 받기
		request.setCharacterEncoding("utf-8");
		int re_num=Integer.parseInt(request.getParameter("re_num"));
		int writer_num = Integer.parseInt(request.getParameter("mem_num"));
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		//로그인 확인
		
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {		//로그인 안됨
			mapAjax.put("result", "logout");
		}else if(user_num!=null && user_num==writer_num){	//로그인 되어있고 로그인한 사용자의 번호가 작성자 번호와 같음
			NoticeDAO dao = NoticeDAO.getInstance();
			dao.deleteNoticeReply(re_num);
			
			mapAjax.put("result", "success");
		}else {		//로그인 되어있으나 로그인한 사용자의 번호가 작성자 번호와 다름.
			mapAjax.put("result", "wrongAccess");
		}
		
		// json문자열 저장
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}

package kr.donation.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.donation.dao.DonationDAO;
import kr.donation.vo.DonationReplyVO;

public class ModifyDonationReplyAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//작성자 번호
		int writer_num = Integer.parseInt(request.getParameter("mem_num"));
		
		Map<String,String> mapAjax = new HashMap<String, String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {	//로그인 안됨
			mapAjax.put("result", "logout");
		}else if(user_num!=null && user_num == writer_num) {		//로그인이 되어있고 로그인한 회원번호와 작성자 회원번호 일치
			DonationReplyVO reply = new DonationReplyVO();
			reply.setRe_num(Integer.parseInt(request.getParameter("re_num")));
			reply.setRe_content(request.getParameter("re_content"));
			reply.setRe_ip(request.getRemoteAddr());
			
			DonationDAO dao = DonationDAO.getInstance();
			dao.updateDonationReply(reply);
			
			mapAjax.put("result", "success");
			
		}else {			//로그인이 되어있고 로그인한 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
		
		//json 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
		
	}
}

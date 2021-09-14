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


public class WriteDonationReplyAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {		//·Î±×ÀÎ ¾ÈµÊ
			mapAjax.put("result","logout");
		}else {
			DonationReplyVO reply = new DonationReplyVO();
			reply.setRe_content(request.getParameter("re_content"));
			reply.setRe_ip(request.getRemoteAddr());
			reply.setMem_num(user_num);
			reply.setDonation_num(Integer.parseInt(request.getParameter("donation_num")));
			
			DonationDAO dao = DonationDAO.getInstance();
			dao.insertDonationReply(reply);
			
			mapAjax.put("result", "success");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}

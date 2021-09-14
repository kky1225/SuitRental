package kr.donation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.donation.dao.DonationDAO;
import kr.donation.vo.DonationVO;

public class ModifyDonationFormAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {	//로그인 안됨
			return "redirect:/member/loginForm.do";
		}
		//로그인 됨.
		
		int donation_num = Integer.parseInt(request.getParameter("donation_num"));
		
		DonationDAO dao = DonationDAO.getInstance();
		DonationVO donation = dao.getDonation(donation_num);
		
		if(user_num !=donation.getMem_num()) {			//작성자 회원번호와 로그인한 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인이 되어있고 로그인한 회원번호와 작성자 회원번호 일치
		//request에 데이터 저장
		request.setAttribute("donation", donation);
				
		
		return "/WEB-INF/views/donation/modifyDonationForm.jsp";
	}
}

package kr.donation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.donation.dao.DonationDAO;
import kr.donation.vo.DonationVO;
import kr.util.FileUtil;

public class DeleteDonationAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 안됨.
			return "redirect:/member/loginForm.do";
		}
		
		//로그인 됨.
		//전송된 데이터 인코딩 처리
		
		int donation_num = Integer.parseInt(request.getParameter("donation_num"));
		
		DonationDAO donationDAO = DonationDAO.getInstance();
		DonationVO donation = donationDAO.getDonation(donation_num);
		if(user_num !=donation.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		donationDAO.deleteDonation(donation_num);					
		//파일삭제
		FileUtil.removeFile(request, donation.getFilename());
		
		
		return "/WEB-INF/views/donation/deleteDonation.jsp";
	}
}

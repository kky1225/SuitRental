package kr.donation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.controller.Action;
import kr.donation.dao.DonationDAO;
import kr.donation.vo.DonationVO;
import kr.util.StringUtil;

public class DetailAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		//글 번호 반환
		int donation_num = Integer.parseInt(request.getParameter("donation_num"));
		DonationDAO dao = DonationDAO.getInstance();
		//조회수 증가
		dao.updateReadCount(donation_num);
		//글 상세페이지
		DonationVO donation = dao.getDonation(donation_num);
		
		//제목에 HTML을 허용하지 않음
		donation.setTitle(StringUtil.useNoHtml(donation.getTitle()));
		//내용에 HTML을 허용하지 않으면서 줄바꿈 처리
		donation.setContent(StringUtil.useBrNoHtml(donation.getContent()));
		
		request.setAttribute("donation", donation);
		
		return "/WEB-INF/views/donation/detail.jsp";
	}
}

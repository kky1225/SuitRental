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
		if(user_num==null) {	//�α��� �ȵ�
			return "redirect:/member/loginForm.do";
		}
		//�α��� ��.
		
		int donation_num = Integer.parseInt(request.getParameter("donation_num"));
		
		DonationDAO dao = DonationDAO.getInstance();
		DonationVO donation = dao.getDonation(donation_num);
		
		if(user_num !=donation.getMem_num()) {			//�ۼ��� ȸ����ȣ�� �α����� ȸ����ȣ�� ����ġ
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//�α����� �Ǿ��ְ� �α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ ��ġ
		//request�� ������ ����
		request.setAttribute("donation", donation);
				
		
		return "/WEB-INF/views/donation/modifyDonationForm.jsp";
	}
}

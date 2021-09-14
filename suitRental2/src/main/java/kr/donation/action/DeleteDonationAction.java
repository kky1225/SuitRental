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
		//�α��� Ȯ��
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//�α��� �ȵ�.
			return "redirect:/member/loginForm.do";
		}
		
		//�α��� ��.
		//���۵� ������ ���ڵ� ó��
		
		int donation_num = Integer.parseInt(request.getParameter("donation_num"));
		
		DonationDAO donationDAO = DonationDAO.getInstance();
		DonationVO donation = donationDAO.getDonation(donation_num);
		if(user_num !=donation.getMem_num()) {
			//�α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ����ġ
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//�α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ��ġ
		donationDAO.deleteDonation(donation_num);					
		//���ϻ���
		FileUtil.removeFile(request, donation.getFilename());
		
		
		return "/WEB-INF/views/donation/deleteDonation.jsp";
	}
}

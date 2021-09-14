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
	
		//�� ��ȣ ��ȯ
		int donation_num = Integer.parseInt(request.getParameter("donation_num"));
		DonationDAO dao = DonationDAO.getInstance();
		//��ȸ�� ����
		dao.updateReadCount(donation_num);
		//�� ��������
		DonationVO donation = dao.getDonation(donation_num);
		
		//���� HTML�� ������� ����
		donation.setTitle(StringUtil.useNoHtml(donation.getTitle()));
		//���뿡 HTML�� ������� �����鼭 �ٹٲ� ó��
		donation.setContent(StringUtil.useBrNoHtml(donation.getContent()));
		
		request.setAttribute("donation", donation);
		
		return "/WEB-INF/views/donation/detail.jsp";
	}
}

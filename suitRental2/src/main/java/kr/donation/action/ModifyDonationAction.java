package kr.donation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.donation.dao.DonationDAO;
import kr.donation.vo.DonationVO;
import kr.util.FileUtil;

public class ModifyDonationAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		//�α��� Ȯ��
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//�α��� �ȵ�
			return "redirect:/member/loginForm.do";
		}
		//�α��� ��.
		
		MultipartRequest multi = FileUtil.createFile(request);
		int donation_num = Integer.parseInt(multi.getParameter("donation_num"));
		String filename=multi.getFilesystemName("filename");
		DonationDAO dao = DonationDAO.getInstance();
		
		DonationVO dbdonation = dao.getDonation(donation_num);
		if(user_num !=dbdonation.getMem_num()) {		//�α��� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ����ġ
			FileUtil.removeFile(request, filename);		//���ε�� ������ ������ ���� ����
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//�α��� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ��ġ
		
		
		DonationVO donation = new DonationVO();
		donation.setDonation_num(donation_num);
		donation.setTitle(multi.getParameter("title"));
		donation.setContent(multi.getParameter("content"));
		donation.setIp(request.getRemoteAddr());
		donation.setFilename(filename);
		
		System.out.println();
		System.out.println("filename : " + multi.getFilesystemName("filename"));
		System.out.println("file_check2 : " + multi.getParameter("file_check2"));

		if(multi.getFilesystemName("filename") == null && multi.getParameter("file_check2").equals("none")) {
			DonationDAO.getInstance().updateDonation(donation, 0);
		}else {
			DonationDAO.getInstance().updateDonation(donation, 1);
		}
		
		return "/WEB-INF/views/donation/modifyDonation.jsp";
	}
}

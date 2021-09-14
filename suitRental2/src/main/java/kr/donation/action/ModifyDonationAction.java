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
		//로그인 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 안됨
			return "redirect:/member/loginForm.do";
		}
		//로그인 됨.
		
		MultipartRequest multi = FileUtil.createFile(request);
		int donation_num = Integer.parseInt(multi.getParameter("donation_num"));
		String filename=multi.getFilesystemName("filename");
		DonationDAO dao = DonationDAO.getInstance();
		
		DonationVO dbdonation = dao.getDonation(donation_num);
		if(user_num !=dbdonation.getMem_num()) {		//로그인 회원번호와 작성자 회원번호가 불일치
			FileUtil.removeFile(request, filename);		//업로드된 파일이 있으면 파일 삭제
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인 회원번호와 작성자 회원번호가 일치
		
		
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

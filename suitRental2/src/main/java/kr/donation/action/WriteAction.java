package kr.donation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.donation.dao.DonationDAO;
import kr.donation.vo.DonationVO;
import kr.util.FileUtil;

public class WriteAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {	//·Î±×ÀÎ ¾ÈµÊ
			return "redirect:/member/loginForm.do";
		}
		
		//·Î±×ÀÎ µÊ.
		
		MultipartRequest multi = FileUtil.createFile(request);
		DonationVO donation = new DonationVO();
		donation.setTitle(multi.getParameter("title"));
		donation.setContent(multi.getParameter("content"));
		donation.setFilename(multi.getFilesystemName("filename"));
		donation.setIp(request.getRemoteAddr());
		donation.setMem_num(user_num);
		
		DonationDAO dao = DonationDAO.getInstance();
		dao.insertDonation(donation);
		
		return "/WEB-INF/views/donation/write.jsp";
	}
}

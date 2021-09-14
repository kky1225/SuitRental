package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;

public class WriteNoticeAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {	//·Î±×ÀÎ ¾ÈµÊ
			return "redirect:/member/loginForm.do";
		}
		
		//·Î±×ÀÎ µÊ.
		MultipartRequest multi = FileUtil.createFile(request);
		NoticeVO notice = new NoticeVO();
		notice.setTitle(multi.getParameter("title"));
		notice.setContent(multi.getParameter("content"));
		notice.setFilename(multi.getFilesystemName("filename"));
		notice.setIp(request.getRemoteAddr());
		notice.setMem_num(user_num);
		
		NoticeDAO dao = NoticeDAO.getInstance();
		dao.insertNotice(notice);
		
		
		return "/WEB-INF/views/notice/writeNotice.jsp";
	}
}

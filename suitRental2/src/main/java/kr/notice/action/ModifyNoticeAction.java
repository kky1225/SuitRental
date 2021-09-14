package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;

public class ModifyNoticeAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		//로그인 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 안됨.
			return "redirect:/member/loginForm.do";
		}
		//로그인 됨.
		
				MultipartRequest multi = FileUtil.createFile(request);
				int notice_num = Integer.parseInt(multi.getParameter("notice_num"));
				String filename=multi.getFilesystemName("filename");
				NoticeDAO dao = NoticeDAO.getInstance();
				
				NoticeVO dbnotice = dao.getNotice(notice_num);
				if(user_num !=dbnotice.getMem_num()) {		//로그인 회원번호와 작성자 회원번호가 불일치
					FileUtil.removeFile(request, filename);		//업로드된 파일이 있으면 파일 삭제
					return "/WEB-INF/views/common/notice.jsp";
				}
				
				//로그인 회원번호와 작성자 회원번호가 일치
				NoticeVO notice = new NoticeVO();
				notice.setNotice_num(notice_num);
				notice.setTitle(multi.getParameter("title"));
				notice.setContent(multi.getParameter("content"));
				notice.setIp(request.getRemoteAddr());
				notice.setFilename(filename);
				
				
				System.out.println();
				System.out.println("filename : " + multi.getFilesystemName("filename"));
				System.out.println("file_check2 : " + multi.getParameter("file_check2"));

				if(multi.getFilesystemName("filename") == null && multi.getParameter("file_check2").equals("none")) {
					NoticeDAO.getInstance().updateNotice(notice, 0);
				}else {
					NoticeDAO.getInstance().updateNotice(notice, 1);
				}
		
		return "/WEB-INF/views/notice/modifyNotice.jsp";
	}
}

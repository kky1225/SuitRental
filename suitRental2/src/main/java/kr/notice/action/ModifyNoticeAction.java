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
		
		//�α��� Ȯ��
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//�α��� �ȵ�.
			return "redirect:/member/loginForm.do";
		}
		//�α��� ��.
		
				MultipartRequest multi = FileUtil.createFile(request);
				int notice_num = Integer.parseInt(multi.getParameter("notice_num"));
				String filename=multi.getFilesystemName("filename");
				NoticeDAO dao = NoticeDAO.getInstance();
				
				NoticeVO dbnotice = dao.getNotice(notice_num);
				if(user_num !=dbnotice.getMem_num()) {		//�α��� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ����ġ
					FileUtil.removeFile(request, filename);		//���ε�� ������ ������ ���� ����
					return "/WEB-INF/views/common/notice.jsp";
				}
				
				//�α��� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ��ġ
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

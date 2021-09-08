package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// ���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		if(member!=null) {	// member�� �����ϸ�
			// ��й�ȣ ��ġ���� üũ
			check = member.isCheckedPassword(passwd);
		}
		
		if(check) {	// check�� true�̸� ���� ����
			// �α��� ó��
			HttpSession session = request.getSession();
			session.setAttribute("user_num", member.getMem_num());
			session.setAttribute("user_id", member.getId());
			session.setAttribute("user_auth", member.getAuth());
			
			// ���� ���������� ȣ��
			return "redirect:/main/main.do";
		}
		
		// ���� ���н� ȣ��
		return "/WEB-INF/views/member/login.jsp";
	}

}

































package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class UpdateAuthAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {	
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		String member_id = request.getParameter("member_id");
		String manager_id = request.getParameter("manager_id");
		String passwd = request.getParameter("passwd");
		int auth = Integer.parseInt(request.getParameter("auth"));
		
		// 회원 아이디 체크
		MemberDAO mem_Dao = MemberDAO.getInstance();
		MemberVO member = mem_Dao.checkMember(member_id);
		
		// 관리자 아이디 체크
		MemberDAO man_Dao = MemberDAO.getInstance();
		MemberVO manager = man_Dao.checkMember(manager_id);
		
		boolean check = false;
		
		if(manager_id.equals(manager.getId()) && member != null && 0 < member.getAuth() && member.getAuth() <= 2) {
			check = manager.isCheckedPassword(passwd);
		}
		
		if(check) {
			mem_Dao.updateAuth(auth,member_id);
		}
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/member/updateAuth.jsp";
	}

}

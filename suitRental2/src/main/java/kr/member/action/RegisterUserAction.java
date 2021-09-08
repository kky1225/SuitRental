package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(request.getParameter("id"));
		memberVO.setPasswd(request.getParameter("passwd"));
		memberVO.setName(request.getParameter("name"));
		memberVO.setPhone(request.getParameter("phone"));
		memberVO.setEmail(request.getParameter("email"));
		memberVO.setZipcode(request.getParameter("zipcode"));
		memberVO.setAddress1(request.getParameter("address1"));
		memberVO.setAddress2(request.getParameter("address2"));
		memberVO.setGender(request.getParameter("gender"));
		
		MemberDAO.getInstance().insertMember(memberVO);
		
		return "/WEB-INF/views/member/registerUser.jsp";
	}

}

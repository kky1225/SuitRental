package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.DAO.BoardQnaDAO;
import kr.board.vo.BoardQnaVO;
import kr.controller.Action;

public class ModifyQnaFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			// 로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		BoardQnaVO boardQna = dao.getBoardQna(qna_num);
		if(user_num != boardQna.getMem_num()) {
			// 로그인한 회원번호와 작성자 회원번호 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		// 로그인 되어있고 로그인한 회원번호와 작성자 회원번호 일치
		request.setAttribute("boardQna", boardQna);
		
		return "/WEB-INF/views/board/modifyQnaForm.jsp";
	}

}

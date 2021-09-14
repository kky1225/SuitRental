package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.DAO.BoardQnaDAO;
import kr.board.vo.BoardQnaVO;
import kr.controller.Action;

public class AnswerQnaAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			// 로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		// 로그인 된 경우
		
		request.setCharacterEncoding("utf-8");
		
		BoardQnaVO boardQna = new BoardQnaVO();
		boardQna.setQna_num(Integer.parseInt(request.getParameter("qna_num")));
		boardQna.setA_content(request.getParameter("a_content"));
		
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		dao.answerBoardQna(boardQna);
		
		return "/WEB-INF/views/board/answerQna.jsp";
	}

}

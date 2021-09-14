package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.DAO.BoardQnaDAO;
import kr.board.vo.BoardQnaVO;
import kr.controller.Action;

public class AnswerQnaFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			// �α��� ���� ���� ���
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		BoardQnaVO boardQna = dao.getBoardQna(qna_num);
		if(user_auth != 3) {
			// �����ڰ� �ƴ�
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		// �α��� �Ǿ��ְ� �α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ ��ġ
		request.setAttribute("boardQna", boardQna);
		
		return "/WEB-INF/views/board/answerQnaForm.jsp";
	}

}

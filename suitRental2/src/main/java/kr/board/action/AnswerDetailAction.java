package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.DAO.BoardQnaDAO;
import kr.board.vo.BoardQnaVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class AnswerDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		dao.updateReadcount(qna_num);
		
		BoardQnaVO answerBoard = dao.getAnswerBoard(qna_num);
		
		answerBoard.setTitle(StringUtil.useBrNoHtml(answerBoard.getTitle()));	// HTML ������� ����
		answerBoard.setQ_content(StringUtil.useBrNoHtml(answerBoard.getQ_content()));	// HTML ������� �����鼭 �ٹٲ� ó��		
		answerBoard.setA_content(StringUtil.useBrNoHtml(answerBoard.getA_content()));	// HTML ������� �����鼭 �ٹٲ� ó��		
		
		request.setAttribute("answerBoard", answerBoard);
		
		return "/WEB-INF/views/board/answerDetail.jsp";
	}

}

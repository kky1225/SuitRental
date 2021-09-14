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
		
		answerBoard.setTitle(StringUtil.useNoHtml(answerBoard.getTitle()));	// HTML 허용하지 않음
		answerBoard.setQ_content(StringUtil.useNoHtml(answerBoard.getQ_content()));	// HTML 허용하지 않으면서 줄바꿈 처리		
		answerBoard.setA_content(StringUtil.useNoHtml(answerBoard.getA_content()));	// HTML 허용하지 않으면서 줄바꿈 처리		
		
		request.setAttribute("answerBoard", answerBoard);
		
		return "/WEB-INF/views/board/answerDetail.jsp";
	}

}

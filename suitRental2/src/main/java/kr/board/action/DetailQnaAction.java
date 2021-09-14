package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.DAO.BoardQnaDAO;
import kr.board.vo.BoardQnaVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailQnaAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		dao.updateReadcount(qna_num);
		
		BoardQnaVO boardQna = dao.getBoardQna(qna_num);
		
		boardQna.setTitle(StringUtil.useNoHtml(boardQna.getTitle()));	// HTML ������� ����
		boardQna.setQ_content(StringUtil.useNoHtml(boardQna.getQ_content()));	// HTML ������� �����鼭 �ٹٲ� ó��		
		
		request.setAttribute("boardQna", boardQna);
		
		return "/WEB-INF/views/board/detailQna.jsp";
	}

}

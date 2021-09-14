package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.DAO.BoardQnaDAO;
import kr.board.vo.BoardQnaVO;
import kr.controller.Action;
import kr.util.PagingUtil;
 
public class ListQnaAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		int count = dao.getBoardQnaCount(keyfield, keyword);
		
		// 페이지 처리 (keyfield, keyword, currentPage, count, rowCount, pageCount, url)
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 20, 10, "listQna.do");
		
		List<BoardQnaVO> list = null;
		if(count > 0) {
			list = dao.getListBoardQna(page.getStartCount(), page.getEndCount(), keyfield, keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("listQna", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
				
		return "/WEB-INF/views/board/listQna.jsp";
	}

}

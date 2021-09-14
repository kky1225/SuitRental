package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.DAO.BoardQnaDAO;
import kr.board.vo.BoardQnaVO;
import kr.controller.Action;
import kr.util.FileUtil;
 
public class WriteQnaAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			// 로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		// 로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		BoardQnaVO boardQna = new BoardQnaVO();
		boardQna.setTitle(multi.getParameter("title"));
		boardQna.setQ_content(multi.getParameter("q_content"));
		boardQna.setIp(request.getRemoteAddr());
		boardQna.setFilename(multi.getFilesystemName("filename"));
		boardQna.setMem_num(user_num);
		
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		dao.insertBoardQna(boardQna);
		
		return "/WEB-INF/views/board/writeQna.jsp";
	}

}

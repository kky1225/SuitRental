package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.DAO.BoardQnaDAO;
import kr.board.vo.BoardQnaVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class ModifyQnaAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			// 로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int qna_num = Integer.parseInt(multi.getParameter("qna_num"));
		String filename = multi.getFilesystemName("filename");
		
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		BoardQnaVO dbBoard = dao.getBoardQna(qna_num);
		if(user_num != dbBoard.getMem_num()) {
			// 로그인한 회원번호와 작성자 회원번호 불일치
			FileUtil.removeFile(request, filename);		// 업로드 된 파일이 있으면 파일삭제
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		// 로그인 되어있고 로그인한 회원번호와 작성자 회원번호 일치
		BoardQnaVO board = new BoardQnaVO();
		board.setQna_num(qna_num);
		board.setTitle(multi.getParameter("title"));
		board.setQ_content(multi.getParameter("q_content"));
		board.setA_content(multi.getParameter("a_content"));
		board.setIp(request.getRemoteAddr());
		board.setFilename(filename);
		
		dao.updateBoard(board);
		
		if(filename != null) {
			// 새파일로 교체 시 기존파일 제거
			FileUtil.removeFile(request, dbBoard.getFilename());
		}
		
		return "redirect:/board/listQna.do";
	}

}

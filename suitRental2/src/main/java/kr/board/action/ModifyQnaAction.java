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
			// �α��� ���� ���� ���
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int qna_num = Integer.parseInt(multi.getParameter("qna_num"));
		String filename = multi.getFilesystemName("filename");
		
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		BoardQnaVO dbBoard = dao.getBoardQna(qna_num);
		if(user_num != dbBoard.getMem_num()) {
			// �α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ ����ġ
			FileUtil.removeFile(request, filename);		// ���ε� �� ������ ������ ���ϻ���
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		// �α��� �Ǿ��ְ� �α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ ��ġ
		BoardQnaVO board = new BoardQnaVO();
		board.setQna_num(qna_num);
		board.setTitle(multi.getParameter("title"));
		board.setQ_content(multi.getParameter("q_content"));
		board.setA_content(multi.getParameter("a_content"));
		board.setIp(request.getRemoteAddr());
		board.setFilename(filename);
		
		dao.updateBoard(board);
		
		if(filename != null) {
			// �����Ϸ� ��ü �� �������� ����
			FileUtil.removeFile(request, dbBoard.getFilename());
		}
		
		return "redirect:/board/listQna.do";
	}

}

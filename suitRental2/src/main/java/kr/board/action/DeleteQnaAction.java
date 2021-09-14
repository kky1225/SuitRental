package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.DAO.BoardQnaDAO;
import kr.board.vo.BoardQnaVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteQnaAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			// �α��� ���� ���� ���
			return "redirect:/member/loginForm.do";
		}
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		BoardQnaDAO dao = BoardQnaDAO.getInstance();
		BoardQnaVO boardQna = dao.getBoardQna(qna_num);
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num != boardQna.getMem_num() && user_auth != 3) {
			// �α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ ����ġ
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		// �α����� �Ǿ��ְ� �α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ ��ġ
		dao.deleteBoardQna(qna_num);
		// ���� ����
		FileUtil.removeFile(request, boardQna.getFilename());
		
		return "redirect:/board/listQna.do";
	}

}

package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class CheckDuplicatedIdAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// ���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		if(member == null) { // ID ���ߺ�
			mapAjax.put("result", "idNotFound");
		}else {	// ID �ߺ�
			mapAjax.put("result", "idDuplicated");
		}
		
		/*
		 * JSON �������� ��ȯ�ϱ⸦ ���ϴ� ���ڿ��� HashMap�� key�� value�� ������ ������ ��
		 * ObjectMapper�� writeValueAsString�� Map ��ü�� �����ؼ� �Ϲ� ���ڿ� �����͸�
		 * JSON ������ ���ڿ� �����ͷ� ��ȯ �� ��ȯ
		 */
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}












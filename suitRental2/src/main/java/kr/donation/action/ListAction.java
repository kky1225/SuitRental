package kr.donation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.controller.Action;
import kr.donation.dao.DonationDAO;
import kr.donation.vo.DonationVO;
import kr.util.PagingUtil;

public class ListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
		String keyfield=request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
	
		if(keyfield == null) {
			keyfield="";
		}
		if(keyword == null) {
			keyword="";
		}
		//�ۼ�
		DonationDAO dao = DonationDAO.getInstance();
		int count = dao.getCount(keyfield,keyword);
		//������ó��
		PagingUtil page = new PagingUtil(keyfield,keyword, Integer.parseInt(pageNum), count, 10, 10, "list.do");
		//list�� �۸�� ��������
		List<DonationVO> list = null; 
		if(count>0) {
			list = dao.getList(page.getStartCount(), page.getEndCount(),keyfield,keyword);
		}
		//������ �ۼ�, list, paginghtml �ֱ�
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		return "/WEB-INF/views/donation/list.jsp";
	}
}

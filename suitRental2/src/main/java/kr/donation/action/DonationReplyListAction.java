package kr.donation.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.donation.dao.DonationDAO;
import kr.donation.vo.DonationReplyVO;
import kr.util.PagingUtil;

public class DonationReplyListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		int donation_num = Integer.parseInt(request.getParameter("donation_num"));
		
		int rowCount=10;
		
		//count수 구하기
		DonationDAO dao = DonationDAO.getInstance();
		int count = dao.getDonationReplyCount(donation_num);
		//page작업
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,rowCount,1,null);
		//list구하기
		List<DonationReplyVO> list = null;
		if(count>0) {
		 list = dao.getDonationReply(page.getStartCount(), page.getEndCount(), donation_num);
		}else{
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapAjax = new HashMap<String, Object>(); 
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}

package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.BookDao;

/**
 * Servlet implementation class borrowServlet
 */
@WebServlet("/borrowServlet")
public class borrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public borrowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// ���ñ�������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		BookDao bookdao = new BookDao();
		// Ϊ�����ֽ���ͻ���Ĺ��ܣ�����tip��tipΪ1����ʾ����
		int tip = Integer.parseInt(request.getParameter("tip"));
		if (tip == 1) {
			// ��ȡͼ��id
			int bid = Integer.parseInt(request.getParameter("bid"));
			int show = Integer.parseInt(request.getParameter("show"));
			HttpSession session = request.getSession();
			AdminBean admin = new AdminBean();
			// ��ȡ������session��aid����id
			String aid = (String) session.getAttribute("aid");
			AdminDao admindao = new AdminDao();
			// ͨ��aid��ȡ�����ߵ���Ϣ
			admin = admindao.get_AidInfo2(aid);
			// �����ļ�¼�������ݱ�
			bookdao.borrowBook(bid, admin);
			if (show == 1) {
				response.sendRedirect("/books/select.jsp");
			} else {
				response.sendRedirect("/books/bdtimes.jsp");
			}
		} else {
			// ���鹦�ܣ���ȡ���ļ�¼��hid
			int hid = Integer.parseInt(request.getParameter("hid"));
			/**
			 * �����ڹ���Ա�Ͷ��߽��涼�У�Ϊ�����֣�������show�ֶΣ�showΪ1��ʾ���߽���
			 */
			int show = Integer.parseInt(request.getParameter("show"));
			// ���û��麯�����ı�status�ֶ�
			bookdao.borrowBook2(hid);
			if (show == 1) {
				response.sendRedirect("/books/borrow.jsp");
			} else {
				response.sendRedirect("/books/admin_borrow.jsp");
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

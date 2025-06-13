package jp.co.akkodis.emp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.akkodis.emp.dao.EmpDao;
import jp.co.akkodis.emp.dto.EmpDto;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", " 検索条件を入力してください");

		RequestDispatcher rd = request.getRequestDispatcher("/disp.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchBtn = request.getParameter("btn"); // 押されたボタン情報
		String paramId = request.getParameter("paramId"); // 入力されたID情報

		String jsp;
		try {
				EmpDao emp = new EmpDao();
				
				if(searchBtn != null && searchBtn.equals("IdSearch")) { 
					ArrayList<EmpDto> empList = emp.findByEmpNo(paramId);
					request.setAttribute("empList", empList);
					jsp = "/disp.jsp";
					
				}else if(searchBtn != null && searchBtn.equals("AgeSearch")) {
					
					String paramAge1 = request.getParameter("paramAge1");
					String paramAge2 = request.getParameter("paramAge2");

					ArrayList<EmpDto> empList = emp.findByBirthday(paramAge1, paramAge2);
					request.setAttribute("empList", empList);

					jsp = "/disp.jsp";
					
				}else {
					request.setAttribute("errorMessage", " 不正アクセスです");
					jsp = "/error.jsp";
				}
				
		}catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "数値を入力してください");
			jsp = "/error.jsp";
		}catch (SQLException e) {
			request.setAttribute("errorMessage", "JDBC のエラーです");
			jsp = "/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", " エラーが発生しました");
			jsp = "/error.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}

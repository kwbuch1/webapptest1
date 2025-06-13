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

import jp.co.akkodis.emp.dao.EmployeeDao;
import jp.co.akkodis.emp.dto.EmployeeDto;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String empNo = request.getParameter("empNo"); // 押されたボタン情報

		String jsp = "/delete.jsp";
		
		try {
				if(empNo != null) { 
					EmployeeDao employeeDao = new EmployeeDao();
					ArrayList<EmployeeDto> employeeList = employeeDao.findByEmpNo(empNo);
					
					if(employeeList==null || employeeList.size()==0) {
						jsp = "/deleteError.jsp";
					}
					else {
						EmployeeDto deleteEmployee = employeeList.get(0);
						request.setAttribute("deleteEmployee", deleteEmployee);
					}
				}else {
					request.setAttribute("errorMessage", "社員番号が誤りです");
					jsp = "/deleteError.jsp";
				}
				
				
			}catch (SQLException e) {
				request.setAttribute("errorMessage", "JDBC のエラーです");
				jsp = "/deleteError.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", " エラーが発生しました");
				jsp = "/deleteError.jsp";
			}
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String deleteBtn = request.getParameter("btn"); 
		
		String empNo = request.getParameter("empno");
		String empName = request.getParameter("empname");

		String jsp = "/disp.jsp";

		try{
			
			if(deleteBtn != null && deleteBtn.equals("DeleteEmp")) {
				EmployeeDto employeeDto = new EmployeeDto();
			
				if (empNo != null && !empNo.isEmpty()){
					
					EmployeeDao employee = new EmployeeDao();
						
					int deleteCount = employee.delete(empNo);
					
					if(deleteCount > 0) {
						request.setAttribute("message", "従業員情報を削除しました");
					}else {
						request.setAttribute("message", "従業員情報を削除できませんでした");
					}
					
				}else {
					request.setAttribute("message", "従業員情報に未入力欄があるため削除できません");
				}
			}else {
				request.setAttribute("errorMessage", " 不正アクセスです");
				jsp = "/deleteError.jsp";
			}
			
		}catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "入力値が不正です");
			jsp = "/deleteError.jsp";
		}catch (SQLException e) {
			request.setAttribute("errorMessage", "JDBC のエラーです");
			e.printStackTrace();
			jsp = "/deleteError.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "エラーが発生しました");
			jsp = "/deleteError.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}

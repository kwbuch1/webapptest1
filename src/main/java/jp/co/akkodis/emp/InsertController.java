package jp.co.akkodis.emp;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.akkodis.emp.dao.EmployeeDao;
import jp.co.akkodis.emp.dto.EmployeeDto;

/**
 * Servlet implementation class InsertController
 */
@WebServlet("/insert")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", " 従業員情報を入力してください。");

		RequestDispatcher rd = request.getRequestDispatcher("/insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String registBtn = request.getParameter("btn"); 
		
		String empNo = request.getParameter("empno");
		String empName = request.getParameter("empname");

		String gender = request.getParameter("gender");

		String paramYear = request.getParameter("year");
		String paramMonth = request.getParameter("month");
		String paramDay = request.getParameter("day");
		
		String birthPlace = request.getParameter("birthPlace");

		String jsp = "/insert.jsp";

		try{
			if(registBtn != null && registBtn.equals("InsertEmp")) {
				EmployeeDto employeeDto = new EmployeeDto();
			
				if (empNo != null && !empNo.isEmpty()
					&& empName != null && !empName.isEmpty()
					&& gender != null && !gender.isEmpty()
					&& paramYear != null && !paramYear.isEmpty()
					&& paramMonth != null && !paramMonth.isEmpty() 
					&& paramDay != null && !paramDay.isEmpty() 
					&& birthPlace != null && !birthPlace.isEmpty()){
					
					int year = Integer.parseInt(paramYear);
					int month = Integer.parseInt(paramMonth);
					int day = Integer.parseInt(paramDay);
				
					EmployeeDao employee = new EmployeeDao();
						
					employeeDto.setEmpNo(empNo);
					employeeDto.setEmpName(empName);
					employeeDto.setGenderCd(gender);
						
					Calendar cal = Calendar.getInstance();
					cal.set(year, month -1, day);
					long datelong = cal.getTimeInMillis();
					Timestamp ts = new Timestamp(datelong);
					employeeDto.setBirthday(ts);
						
					employeeDto.setBirthPlace(birthPlace);
						
					int insertCount = employee.create(employeeDto);
					
					
					if(insertCount >0 ) {
						request.setAttribute("message", "従業員情報を登録しました");
					}else {
						request.setAttribute("message", "従業員情報を登録できませんでした");
					}
				}else {
					request.setAttribute("message", "従業員情報に未入力欄があるため登録できません");
				}
			}else {
				request.setAttribute("errorMessage", " 不正アクセスです");
				jsp = "/insertError.jsp";
			}
			
		}catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "入力値が不正です");
			jsp = "/insertError.jsp";
		}catch (SQLException e) {
			request.setAttribute("errorMessage", "JDBC のエラーです");
			e.printStackTrace();
			jsp = "/insertError.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "エラーが発生しました");
			jsp = "/insertError.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);

	}

}

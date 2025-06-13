package jp.co.akkodis.emp;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.akkodis.emp.dao.EmployeeDao;
import jp.co.akkodis.emp.dao.PrefectureDao;
import jp.co.akkodis.emp.dto.EmployeeDto;
import jp.co.akkodis.emp.dto.PrefectureDto;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String empNo = request.getParameter("empNo"); // 押されたボタン情報

		String jsp = "/update.jsp";
		
		try {
				if(empNo != null) { 
					EmployeeDao employeeDao = new EmployeeDao();
					ArrayList<EmployeeDto> employeeList = employeeDao.findByEmpNo(empNo);
					EmployeeDto updateEmployee = employeeList.get(0);
					request.setAttribute("updateEmployee", updateEmployee);
					
					PrefectureDao prefectureDao = new PrefectureDao();
					ArrayList<PrefectureDto> prefectureList = prefectureDao.findAll();
					
					String birthPlaceCd = updateEmployee.getBirthPlace();
					
					String optionTags = "";
					for(PrefectureDto prefecture : prefectureList) {
						
						String prefcd = prefecture.getPrefcd();
						String prefname = prefecture.getPrefname();
						String selected = "";
						
						if(birthPlaceCd.equals(prefcd)){
							selected = "selected";
						}
						
						String optionTag = "<option value='" + prefcd + "' " + selected + ">" + prefname + "</option>";
						optionTags += optionTag;
					}
					
					request.setAttribute("optionTags", optionTags);
				}else {
					jsp = "/updateError.jsp";
				}
				
				
			}catch (SQLException e) {
				request.setAttribute("errorMessage", "JDBC のエラーです");
				jsp = "/updateError.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", " エラーが発生しました");
				jsp = "/updateError.jsp";
			}
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String updateBtn = request.getParameter("btn"); 
		
		String empNo = request.getParameter("empno");
		String empName = request.getParameter("empname");

		String gender = request.getParameter("gender");

		String paramYear = request.getParameter("year");
		String paramMonth = request.getParameter("month");
		String paramDay = request.getParameter("day");
		
		String birthPlace = request.getParameter("birthPlace");

		String jsp = "/disp.jsp";

		try{
			
			if(updateBtn != null && updateBtn.equals("UpdateEmp")) {
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
						
					int insertCount = employee.update(employeeDto);
					
					if(insertCount > 0) {
						request.setAttribute("message", "従業員情報を更新しました");
					}else {
						request.setAttribute("message", "従業員情報を更新できませんでした");
					}
					
				}else {
					request.setAttribute("message", "従業員情報に未入力欄があるため更新できません");
				}
			}else {
				request.setAttribute("errorMessage", " 不正アクセスです");
				jsp = "/updateError.jsp";
			}
			
		}catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "入力値が不正です");
			jsp = "/updateError.jsp";
		}catch (SQLException e) {
			request.setAttribute("errorMessage", "JDBC のエラーです");
			e.printStackTrace();
			jsp = "/updateError.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "エラーが発生しました");
			jsp = "/updateError.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}

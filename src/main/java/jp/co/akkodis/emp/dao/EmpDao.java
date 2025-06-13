package jp.co.akkodis.emp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.Calendar;

import jp.co.akkodis.emp.dto.EmpDto;

public class EmpDao extends Dao{

	  
	public EmpDao() throws ClassNotFoundException, SQLException{
		super();
	}
	
	public ArrayList<EmpDto> findByEmpNo(String paramId) throws SQLException {
		ArrayList<EmpDto> empList = new ArrayList<EmpDto>();
		
		try {
			String sql = "select * from (select empno, empname, gendername, date_format(birthday, '%Y/%m/%d') as 'birthday'" 
						+ " from employee e inner join gender g on e.gendercd = g.gendercd) emp where empno = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, paramId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				EmpDto empDto = new EmpDto();
				empDto.setEmpNo(rs.getString(1));
				empDto.setEmpName(rs.getString(2));
				empDto.setGenderName(rs.getString(3));
				empDto.setBirthday(rs.getString(4));
				
				empList.add(empDto);
			}
		}finally {
			close();
		}
  
		return empList;
	}
	
	public ArrayList<EmpDto> findByBirthday(String paramAge1, String paramAge2) throws SQLException, NumberFormatException {
		ArrayList<EmpDto> empList = new ArrayList<EmpDto>();
		
		try {
			int age1 = Integer.parseInt(paramAge1);
			int age2 = Integer.parseInt(paramAge2);
			
			String sql = "select * from (select empno, empname, gendername, date_format(birthday, '%Y/%m/%d') as 'birthday'"
					+ "from employee e inner join gender g on e.gendercd = g.gendercd) emp where empno in ("
					+ "SELECT empno FROM employee WHERE birthday <= ( DATE_ADD( NOW(), INTERVAL -? YEAR ))"
					+ "AND birthday >= ( DATE_ADD( NOW(), INTERVAL -? YEAR )))";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, age1);
			ps.setInt(2, age2);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				EmpDto empDto = new EmpDto();
				empDto.setEmpNo(rs.getString(1));
				empDto.setEmpName(rs.getString(2));
				empDto.setGenderName(rs.getString(3));
				empDto.setBirthday(rs.getString(4));
				
				empList.add(empDto);
			}
			
		}finally {
			close();
		}
		
		return empList;
	}
	
}

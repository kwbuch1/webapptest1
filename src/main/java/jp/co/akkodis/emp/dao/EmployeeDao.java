package jp.co.akkodis.emp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.akkodis.emp.dto.EmployeeDto;

public class EmployeeDao extends Dao {
	
	public EmployeeDao() throws ClassNotFoundException, SQLException{
		super();
	}
	
	public ArrayList<EmployeeDto> findByEmpNo(String paramId) throws SQLException {
		ArrayList<EmployeeDto> employeeList = new ArrayList<EmployeeDto>();
		
		try {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPNO = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, paramId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				EmployeeDto employeeDto = new EmployeeDto();
				employeeDto.setEmpNo(rs.getString(1));
				employeeDto.setEmpName(rs.getString(2));
				employeeDto.setGenderCd(rs.getString(3));
				employeeDto.setBirthday(rs.getTimestamp(4));
				employeeDto.setBirthPlace(rs.getString(5));
				
				employeeList.add(employeeDto);
			}
		}finally {
			close();
		}
  
		return employeeList;
	}
	
	
	public int create(EmployeeDto dto) throws SQLException {
		
		try {
			String sql = "INSERT INTO EMPLOYEE VALUES ( ?, ?, ?, ?, ?)";
	
			PreparedStatement ps = connection.prepareStatement(sql);
			System.out.println(dto.getEmpName());
			ps.setString(1,dto.getEmpNo());
			ps.setString(2,dto.getEmpName());
			ps.setString(3,dto.getGenderCd());
			ps.setTimestamp(4, dto.getBirthday());
			ps.setString(5, dto.getBirthPlace());
			
			int resultCount = ps.executeUpdate();
			return resultCount;
		
		}finally {
			close();
		}
	}
	
	public int update(EmployeeDto dto) throws SQLException {
		try{
			String sql = "UPDATE EMPLOYEE SET EMPNO = ?, EMPNAME = ?, GENDERCD = ?,"
				+ " BIRTHDAY = ?, BIRTHPLACE = ? WHERE EMPNO = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, dto.getEmpNo());
			ps.setString(2, dto.getEmpName());
			ps.setString(3, dto.getGenderCd());
			ps.setTimestamp(4, dto.getBirthday());
			ps.setString(5, dto.getBirthPlace());
			ps.setString(6, dto.getEmpNo());
			
			int resultCount = ps.executeUpdate();
			
			return resultCount;
		}finally {
			close();
		}
	}
	
	public int delete(String empNo) throws SQLException {
		try{
			String sql = "DELETE FROM EMPLOYEE WHERE EMPNO = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);			
			ps.setString(1, empNo);
			
			int resultCount = ps.executeUpdate();
			
			return resultCount;
		}finally {
			close();
		}
	}
	
}

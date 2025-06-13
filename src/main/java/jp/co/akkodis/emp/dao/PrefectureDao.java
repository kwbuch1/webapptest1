package jp.co.akkodis.emp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jp.co.akkodis.emp.dto.PrefectureDto;

public class PrefectureDao extends Dao{

	public PrefectureDao() throws ClassNotFoundException, SQLException{
		super();
	}
	
	public ArrayList<PrefectureDto> findAll() throws SQLException {
		String sql = "select * from Prefecture order by prefcd";

		ArrayList<PrefectureDto> prefectureList = new ArrayList<PrefectureDto>();

		Statement state = connection.createStatement();
		ResultSet rs = state.executeQuery(sql);

		while(rs.next()) {
			PrefectureDto prefecture = new PrefectureDto();
			prefecture.setPrefcd(rs.getString(1));
			prefecture.setPrefname(rs.getString(2));

			prefectureList.add(prefecture);
		}

		return prefectureList;
	}
}

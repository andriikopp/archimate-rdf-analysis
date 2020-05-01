package ua.khpi.analysis.dao;

import java.sql.Timestamp;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;
import java.util.Map;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import ua.khpi.analysis.beans.Artifact;

public class ModelAnalysisDAO {
	private Sql2o sql2o;

	public ModelAnalysisDAO() {
		this.sql2o = new Sql2o("jdbc:mysql://localhost:3306/test", "root", "");
	}

	public void insertAnalysisResults(String map, Artifact artifact, Timestamp timestamp) {
		String insertSql = "insert into ea_prm(prm_map, prm_timestamp, prm_year, prm_month, prm_day, prm_proc, prm_type, prm_indeg, prm_outdeg, prm_centr, prm_rank, prm_pc) "
				+ "values (:prm_map, :prm_timestamp, :prm_year, :prm_month, :prm_day, :prm_proc, :prm_type, :prm_indeg, :prm_outdeg, :prm_centr, :prm_rank, :prm_pc)";

		try (Connection con = sql2o.open()) {
			con.createQuery(insertSql).addParameter("prm_map", map).addParameter("prm_year", Year.now().getValue())
					.addParameter("prm_timestamp", timestamp).addParameter("prm_month", MonthDay.now().getMonthValue())
					.addParameter("prm_day", MonthDay.now().getDayOfMonth())
					.addParameter("prm_proc", artifact.getName()).addParameter("prm_type", artifact.getType())
					.addParameter("prm_indeg", artifact.getIncomingNodes().size())
					.addParameter("prm_outdeg", artifact.getOutgoingNodes().size())
					.addParameter("prm_centr", artifact.getCentrality()).addParameter("prm_rank", artifact.getRank())
					.addParameter("prm_pc", artifact.getCost()).executeUpdate();
		}
	}

	public List<Map<String, Object>> readAnalysisResults() {
		String sql = "select * from ea_prm";

		try (Connection con = sql2o.open()) {
			return con.createQuery(sql).executeAndFetchTable().asList();
		}
	}
}

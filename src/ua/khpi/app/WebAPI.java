package ua.khpi.app;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import ua.khpi.analysis.dao.ModelAnalysisDAO;

public class WebAPI {
	private ModelAnalysisDAO dao = new ModelAnalysisDAO();

	public static void main(String[] args) {
		WebAPI api = new WebAPI();

		get("/api/read", (req, res) -> {
			List<Map<String, Object>> nativeList = new ArrayList<>();

			for (Map<String, Object> record : api.getDao().readAnalysisResults()) {
				Map<String, Object> nativeMap = new LinkedHashMap<>();

				for (String key : record.keySet()) {
					nativeMap.put(key, record.get(key));
				}

				nativeList.add(nativeMap);
			}

			return new Gson().toJson(nativeList);
		});
	}

	public ModelAnalysisDAO getDao() {
		return dao;
	}

	public void setDao(ModelAnalysisDAO dao) {
		this.dao = dao;
	}
}

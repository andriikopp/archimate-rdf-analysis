package ua.khpi.apparchi.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.khpi.apparchi.Context;
import ua.khpi.apparchi.dao.api.IMeasureDAO;
import ua.khpi.apparchi.dao.api.IModelDAO;
import ua.khpi.apparchi.dao.api.ISuggestionDAO;
import ua.khpi.apparchi.entity.MeasureEntity;
import ua.khpi.apparchi.entity.ModelEntity;
import ua.khpi.apparchi.entity.SuggestionEntity;
import ua.khpi.apparchi.entity.api.IGenericEntity;
import ua.khpi.apparchi.service.api.ISuggestionService;
import ua.khpi.apparchi.util.RecommendationUtil;
import ua.khpi.apparchi.util.SimilarityUtil;

public class ArchiMateSuggestionService implements ISuggestionService {
	private IModelDAO modelDAO;
	private IMeasureDAO measureDAO;
	private ISuggestionDAO suggestionDAO;

	@Override
	public void suggestModel(ModelEntity model) {
		Map<String, MeasureEntity> measuresMap = new HashMap<>();

		for (IGenericEntity entity : this.measureDAO.readAll()) {
			MeasureEntity measureEntity = (MeasureEntity) entity;
			measuresMap.put(measureEntity.getModelId(), measureEntity);
		}
		
		MeasureEntity currentModelMeasures = measuresMap.get(model.getId());
		List<SuggestionEntity> allSuggestions = new ArrayList<>();
		
		for (IGenericEntity entity : this.modelDAO.readAll()) {
			ModelEntity modelEntity = (ModelEntity) entity;
			
			if (modelEntity.getDescription().equals(Context.PATTERN)) {
				MeasureEntity compareModelMeasures = measuresMap.get(modelEntity.getId());
				double distance = SimilarityUtil.euclideanDistance(currentModelMeasures, compareModelMeasures);
				String recommendation = RecommendationUtil.provideRecommendation(modelEntity.getTitle());
				SuggestionEntity suggestionEntity = new SuggestionEntity(model.getId() + "R", 
						model.getId(), 
						modelEntity.getTitle(), 
						distance, 
						recommendation, 
						model.getTimestamp());
				allSuggestions.add(suggestionEntity);
			}
		}
		
		allSuggestions.sort(new Comparator<SuggestionEntity>() {

			@Override
			public int compare(SuggestionEntity a, SuggestionEntity b) {
				return Double.valueOf(a.getSimilarity()).compareTo(Double.valueOf(b.getSimilarity()));
			}
			
		});
		
		this.suggestionDAO.create(allSuggestions.get(0));
	}

	public IModelDAO getModelDAO() {
		return modelDAO;
	}

	public void setModelDAO(IModelDAO modelDAO) {
		this.modelDAO = modelDAO;
	}

	public IMeasureDAO getMeasureDAO() {
		return measureDAO;
	}

	public void setMeasureDAO(IMeasureDAO measureDAO) {
		this.measureDAO = measureDAO;
	}

	public ISuggestionDAO getSuggestionDAO() {
		return suggestionDAO;
	}

	public void setSuggestionDAO(ISuggestionDAO suggestionDAO) {
		this.suggestionDAO = suggestionDAO;
	}

}

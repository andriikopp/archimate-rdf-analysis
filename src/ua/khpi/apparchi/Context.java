package ua.khpi.apparchi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import ua.khpi.apparchi.dao.ListElementDAO;
import ua.khpi.apparchi.dao.ListMeasureDAO;
import ua.khpi.apparchi.dao.ListModelDAO;
import ua.khpi.apparchi.dao.ListStructureDAO;
import ua.khpi.apparchi.dao.ListSuggestionDAO;
import ua.khpi.apparchi.dao.api.IElementDAO;
import ua.khpi.apparchi.dao.api.IMeasureDAO;
import ua.khpi.apparchi.dao.api.IModelDAO;
import ua.khpi.apparchi.dao.api.IStructureDAO;
import ua.khpi.apparchi.dao.api.ISuggestionDAO;
import ua.khpi.apparchi.dao.api.generic.IGenericDAO;
import ua.khpi.apparchi.entity.ElementEntity;
import ua.khpi.apparchi.entity.MeasureEntity;
import ua.khpi.apparchi.entity.ModelEntity;
import ua.khpi.apparchi.entity.StructureEntity;
import ua.khpi.apparchi.entity.SuggestionEntity;
import ua.khpi.apparchi.entity.api.IGenericEntity;
import ua.khpi.apparchi.service.ArchiMateMeasurementService;
import ua.khpi.apparchi.service.ArchiMateSuggestionService;
import ua.khpi.apparchi.service.ArchiMateTranslationService;
import ua.khpi.apparchi.service.api.IMeasurementService;
import ua.khpi.apparchi.service.api.ISuggestionService;
import ua.khpi.apparchi.service.api.ITranslationService;

import static spark.Spark.*;

public class Context {	
	public static final String PATTERN = "@pattern";
	
	private IModelDAO modelDAO;
	private IStructureDAO structureDAO;
	private IElementDAO elementDAO;
	private IMeasureDAO measureDAO;
	private ISuggestionDAO suggestionDAO;
	
	private ITranslationService translationService;
	private IMeasurementService measurementService;
	private ISuggestionService suggestionService;

	public Context() {
		this.modelDAO = new ListModelDAO();
		this.structureDAO = new ListStructureDAO();
		this.elementDAO = new ListElementDAO();
		this.measureDAO = new ListMeasureDAO();
		this.suggestionDAO = new ListSuggestionDAO();
		
		this.translationService = new ArchiMateTranslationService();
		((ArchiMateTranslationService) this.translationService).setModelDAO(this.modelDAO);
		((ArchiMateTranslationService) this.translationService).setStructureDAO(this.structureDAO);
		((ArchiMateTranslationService) this.translationService).setElementDAO(this.elementDAO);
		
		this.measurementService = new ArchiMateMeasurementService();
		((ArchiMateMeasurementService) this.measurementService).setModelDAO(this.modelDAO);
		((ArchiMateMeasurementService) this.measurementService).setStructureDAO(this.structureDAO);
		((ArchiMateMeasurementService) this.measurementService).setElementDAO(this.elementDAO);
		((ArchiMateMeasurementService) this.measurementService).setMeasureDAO(this.measureDAO);
		
		this.suggestionService = new ArchiMateSuggestionService();
		((ArchiMateSuggestionService) this.suggestionService).setModelDAO(this.modelDAO);
		((ArchiMateSuggestionService) this.suggestionService).setMeasureDAO(this.measureDAO);
		((ArchiMateSuggestionService) this.suggestionService).setSuggestionDAO(this.suggestionDAO);
	}

	public void run() {
		String timestamp = new Date().toString();
		
		// Register patterns
		ModelEntity[] patterns = {
				new ModelEntity(null,
						"Sequential",
						"sequential.xml",
						timestamp,
						PATTERN,
						"Me",
						"Thesis",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Ring",
						"ring.xml",
						timestamp,
						PATTERN,
						"Me",
						"Thesis",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Radial",
						"radial.xml",
						timestamp,
						PATTERN,
						"Me",
						"Thesis",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Tree",
						"tree.xml",
						timestamp,
						PATTERN,
						"Me",
						"Thesis",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Mesh",
						"mesh.xml",
						timestamp,
						PATTERN,
						"Me",
						"Thesis",
						"System Design",
						"KhPI")
		};
		
		// Parse patterns
		int patternNum = 1;
		
		for (ModelEntity model : patterns) {
			model.setId("P" + String.valueOf(patternNum++));
			this.translationService.translateModel(model);
		};
		
		// Measure patterns
		for (ModelEntity model : patterns) {
			this.measurementService.measureModel(model);
		};
		
		ModelEntity[] models = {
				new ModelEntity(null,
						"Application Controller",
						"ApplicationController.xml",
						timestamp,
						"Web Presentation Pattern",
						"Martin Fowler",
						"Patterns of Enterprise Application Architecture",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Front Controller",
						"FrontController.xml",
						timestamp,
						"Web Presentation Pattern",
						"Martin Fowler",
						"Patterns of Enterprise Application Architecture",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Model View Controller",
						"ModelViewController.xml",
						timestamp,
						"Web Presentation Pattern",
						"Martin Fowler",
						"Patterns of Enterprise Application Architecture",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Page Controller",
						"PageController.xml",
						timestamp,
						"Web Presentation Pattern",
						"Martin Fowler",
						"Patterns of Enterprise Application Architecture",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Template View",
						"TemplateView.xml",
						timestamp,
						"Web Presentation Pattern",
						"Martin Fowler",
						"Patterns of Enterprise Application Architecture",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Transform View",
						"TransformView.xml",
						timestamp,
						"Web Presentation Pattern",
						"Martin Fowler",
						"Patterns of Enterprise Application Architecture",
						"System Design",
						"KhPI"),
				new ModelEntity(null,
						"Two-Step View",
						"TwoStepView.xml",
						timestamp,
						"Web Presentation Pattern",
						"Martin Fowler",
						"Patterns of Enterprise Application Architecture",
						"System Design",
						"KhPI")
		};
		
		// Parse models
		int modelNum = 1;
		
		for (ModelEntity model : models) {
			model.setId("M" + String.valueOf(modelNum++));
			this.translationService.translateModel(model);
		};
		
		// Measure models
		for (ModelEntity model : models) {
			this.measurementService.measureModel(model);
		};
		
		// Print measured patterns and models
		for (IGenericEntity measureEntity : this.measureDAO.readAll()) {
			System.out.println((MeasureEntity) measureEntity);
		}
		
		// Analyze models
		for (ModelEntity model : models) {
			this.suggestionService.suggestModel(model);
		};
		
		// Print suggestions
		for (IGenericEntity suggestionEntity : this.suggestionDAO.readAll()) {
			System.out.println((SuggestionEntity) suggestionEntity);
		}
		
		// Web API
		List<ElementEntity> elementList = castDAO(this.elementDAO);
		List<MeasureEntity> measureList = castDAO(this.measureDAO);
		List<ModelEntity> modelList = castDAO(this.modelDAO);
		List<StructureEntity> structureList = castDAO(this.structureDAO);
		List<SuggestionEntity> suggestionList = castDAO(this.suggestionDAO);
		
		path("/api", () -> {
			get("/element", (req, res) -> new Gson().toJson(elementList));
			get("/measure", (req, res) -> new Gson().toJson(measureList));
			get("/model", (req, res) -> new Gson().toJson(modelList));
			get("/structure", (req, res) -> new Gson().toJson(structureList));
			get("/suggestion", (req, res) -> new Gson().toJson(suggestionList));
		});
		
		saveConfigs(patterns, "patterns.json");
		saveConfigs(models, "models.json");
	}
	
	@SuppressWarnings("unchecked")
	public <T extends IGenericEntity> List<T> castDAO(IGenericDAO daoObject) {
		List<T> list = new ArrayList<T>();
		
		for (IGenericEntity obj : daoObject.readAll()) {
			list.add((T) obj);
		}
		
		return list;
	}
	
	public void saveConfigs(ModelEntity[] models, String fileName) {
		try {
			FileWriter myWriter = new FileWriter(fileName);
			myWriter.write(new Gson().toJson(models));
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Context().run();
	}

}

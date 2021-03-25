package ua.khpi.apparchi;

import java.util.Date;
import java.util.UUID;

import ua.khpi.apparchi.dao.api.IElementDAO;
import ua.khpi.apparchi.dao.api.IMeasureDAO;
import ua.khpi.apparchi.dao.api.IModelDAO;
import ua.khpi.apparchi.dao.api.IStructureDAO;
import ua.khpi.apparchi.dao.api.ISuggestionDAO;
import ua.khpi.apparchi.entity.ModelEntity;
import ua.khpi.apparchi.service.ArchiMateTranslationService;
import ua.khpi.apparchi.service.api.ITranslationService;

public class Context {
	private static final String FILES_PREFIX = "apparchi\\input\\";
	
	private IModelDAO modelDAO;
	private IStructureDAO structureDAO;
	private IElementDAO elementDAO;
	private IMeasureDAO measureDAO;
	private ISuggestionDAO suggestionDAO;
	
	private ITranslationService translationService;

	public Context() {
		this.translationService = new ArchiMateTranslationService();
		((ArchiMateTranslationService) this.translationService).setModelDAO(modelDAO);
		((ArchiMateTranslationService) this.translationService).setStructureDAO(structureDAO);
		((ArchiMateTranslationService) this.translationService).setElementDAO(elementDAO);
	}

	public void run() {
		// Create model
		ModelEntity model = new ModelEntity(UUID.randomUUID().toString(),
				"Archisurance Example",
				FILES_PREFIX + "Archisurance.xml",
				new Date().toString(),
				"This is an ArchiMate example model of insurance company",
				"The Open Group",
				"ArchiMate",
				"Insurance",
				"Archisurance");
		
		// Parse models
		this.translationService.translateModel(model);
	}

	public static void main(String[] args) {
		new Context().run();
	}

}

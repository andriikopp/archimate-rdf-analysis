package ua.khpi.apparchi.service;

import java.util.UUID;

import ua.khpi.apparchi.dao.api.IElementDAO;
import ua.khpi.apparchi.dao.api.IMeasureDAO;
import ua.khpi.apparchi.dao.api.IModelDAO;
import ua.khpi.apparchi.dao.api.IStructureDAO;
import ua.khpi.apparchi.entity.ElementEntity;
import ua.khpi.apparchi.entity.MeasureEntity;
import ua.khpi.apparchi.entity.ModelEntity;
import ua.khpi.apparchi.entity.StructureEntity;
import ua.khpi.apparchi.entity.api.IGenericEntity;
import ua.khpi.apparchi.service.api.IMeasurementService;

public class ArchiMateMeasurementService implements IMeasurementService {

	private IModelDAO modelDAO;
	private IStructureDAO structureDAO;
	private IElementDAO elementDAO;
	private IMeasureDAO measureDAO;

	@Override
	public void measureModel(ModelEntity model) {
		double density = 0;
		double avgDegree = 0;
		double minDegree = Double.MAX_VALUE;
		double maxDegree = Double.MIN_NORMAL;
		double connectivity = 0;
		double resilience = 0;
		double centrality = 0;
		double irregularity = 0;
		StructureEntity structureEntity = null;
		
		for (IGenericEntity entity : this.structureDAO.readAll()) {
			if (((StructureEntity) entity).getModelId().equals(model.getId())) {
				structureEntity = ((StructureEntity)entity);
			}
		}
		
		density = (double) structureEntity.getEdges() / (structureEntity.getNodes() * (structureEntity.getNodes() - 1));
		
		for (IGenericEntity entity : this.elementDAO.readAll()) {
			ElementEntity element = (ElementEntity) entity;
			
			if (element.getStructureId().equals(structureEntity.getId())) {
				int degree = element.getInDegree() + element.getOutDegree();
				avgDegree += degree;
				
				if (degree < minDegree) {
					minDegree = degree;
				}
				
				if (degree > maxDegree) {
					maxDegree = degree;
				}
				
				connectivity += degree;
			}
		}
		
		avgDegree /= structureEntity.getNodes();
		connectivity /= 2.0;
		resilience = connectivity * (1.0 / (structureEntity.getNodes() - 1.0)) - 1.0;
		
		for (IGenericEntity entity : this.elementDAO.readAll()) {
			ElementEntity element = (ElementEntity) entity;
			
			if (element.getStructureId().equals(structureEntity.getId())) {
				int degree = element.getInDegree() + element.getOutDegree();
				centrality += maxDegree - degree;
				irregularity += Math.pow(degree - (2 * structureEntity.getEdges() / structureEntity.getNodes()), 2);
			}
		}
		
		centrality /= ((structureEntity.getNodes() - 1.0) * (structureEntity.getNodes() - 2.0));
		irregularity = Math.sqrt(irregularity);
		MeasureEntity measureEntity = new MeasureEntity(UUID.randomUUID().toString(),
				model.getId(),
				density,
				avgDegree,
				minDegree,
				maxDegree,
				connectivity,
				resilience,
				centrality,
				irregularity,
				model.getTimestamp());
		this.measureDAO.create(measureEntity);
	}

	public IModelDAO getModelDAO() {
		return modelDAO;
	}

	public void setModelDAO(IModelDAO modelDAO) {
		this.modelDAO = modelDAO;
	}

	public IStructureDAO getStructureDAO() {
		return structureDAO;
	}

	public void setStructureDAO(IStructureDAO structureDAO) {
		this.structureDAO = structureDAO;
	}

	public IElementDAO getElementDAO() {
		return elementDAO;
	}

	public void setElementDAO(IElementDAO elementDAO) {
		this.elementDAO = elementDAO;
	}

	public IMeasureDAO getMeasureDAO() {
		return measureDAO;
	}

	public void setMeasureDAO(IMeasureDAO measureDAO) {
		this.measureDAO = measureDAO;
	}

}

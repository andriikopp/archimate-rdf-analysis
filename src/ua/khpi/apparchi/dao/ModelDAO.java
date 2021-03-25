package ua.khpi.apparchi.dao;

import java.util.ArrayList;
import java.util.List;

import ua.khpi.apparchi.dao.api.IModelDAO;
import ua.khpi.apparchi.entity.ModelEntity;
import ua.khpi.apparchi.entity.api.IGenericEntity;

public class ModelDAO implements IModelDAO {
	private List<ModelEntity> models;

	public ModelDAO() {
		this.models = new ArrayList<>();
	}

	@Override
	public void create(IGenericEntity obj) {
		models.add((ModelEntity) obj);
	}

	@Override
	public ModelEntity read(String id) {
		for (ModelEntity model : models) {
			if (model.getId().equals(id)) {
				return model;
			}
		}

		return null;
	}

	@Override
	public void update(String id, IGenericEntity obj) {
		throw new IllegalAccessError();
	}

	@Override
	public void delete(String id) {
		throw new IllegalAccessError();
	}

}

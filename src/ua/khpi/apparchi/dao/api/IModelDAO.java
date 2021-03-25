package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.entity.ModelEntity;

public interface IModelDAO extends IGenericDAO {

	void create(ModelEntity obj);

	ModelEntity read(String id);

	void update(String id, ModelEntity obj);

	void delete(String id);
}

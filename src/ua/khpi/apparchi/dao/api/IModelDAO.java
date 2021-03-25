package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.dao.api.generic.IGenericDAO;
import ua.khpi.apparchi.entity.ModelEntity;
import ua.khpi.apparchi.entity.api.IGenericEntity;

public interface IModelDAO extends IGenericDAO {

	void create(IGenericEntity obj);

	ModelEntity read(String id);

	void update(String id, IGenericEntity obj);

	void delete(String id);
}

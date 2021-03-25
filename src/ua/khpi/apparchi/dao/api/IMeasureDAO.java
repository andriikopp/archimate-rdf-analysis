package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.dao.api.generic.IGenericDAO;
import ua.khpi.apparchi.entity.MeasureEntity;
import ua.khpi.apparchi.entity.api.IGenericEntity;

public interface IMeasureDAO extends IGenericDAO {

	void create(IGenericEntity obj);

	MeasureEntity read(String id);

	void update(String id, IGenericEntity obj);

	void delete(String id);
}

package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.entity.MeasureEntity;

public interface IMeasureDAO extends IGenericDAO {

	void create(MeasureEntity obj);

	MeasureEntity read(String id);

	void update(String id, MeasureEntity obj);

	void delete(String id);
}

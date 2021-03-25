package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.entity.api.IGenericEntity;

public interface IGenericDAO {

	void create(IGenericEntity obj);

	IGenericEntity read(String id);

	void update(String id, IGenericEntity obj);

	void delete(String id);
}

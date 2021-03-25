package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.dao.api.generic.IGenericDAO;
import ua.khpi.apparchi.entity.ElementEntity;
import ua.khpi.apparchi.entity.api.IGenericEntity;

public interface IElementDAO extends IGenericDAO {

	void create(IGenericEntity obj);

	ElementEntity read(String id);

	void update(String id, IGenericEntity obj);

	void delete(String id);
}

package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.dao.api.generic.IGenericDAO;
import ua.khpi.apparchi.entity.ElementEntity;

public interface IElementDAO extends IGenericDAO {

	void create(ElementEntity obj);

	ElementEntity read(String id);

	void update(String id, ElementEntity obj);

	void delete(String id);
}

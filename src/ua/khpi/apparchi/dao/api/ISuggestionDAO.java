package ua.khpi.apparchi.dao.api;

import java.util.List;

import ua.khpi.apparchi.dao.api.generic.IGenericDAO;
import ua.khpi.apparchi.entity.api.IGenericEntity;

public interface ISuggestionDAO extends IGenericDAO {

	void create(IGenericEntity obj);

	IGenericEntity read(String id);

	List<IGenericEntity> readAll();

	void update(String id, IGenericEntity obj);

	void delete(String id);
}

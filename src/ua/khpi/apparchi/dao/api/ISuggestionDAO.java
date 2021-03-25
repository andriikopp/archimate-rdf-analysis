package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.dao.api.generic.IGenericDAO;
import ua.khpi.apparchi.entity.SuggestionEntity;
import ua.khpi.apparchi.entity.api.IGenericEntity;

public interface ISuggestionDAO extends IGenericDAO {

	void create(IGenericEntity obj);

	SuggestionEntity read(String id);

	void update(String id, IGenericEntity obj);

	void delete(String id);
}

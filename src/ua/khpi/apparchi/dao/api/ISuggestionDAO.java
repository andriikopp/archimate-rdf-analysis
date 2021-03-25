package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.dao.api.generic.IGenericDAO;
import ua.khpi.apparchi.entity.SuggestionEntity;

public interface ISuggestionDAO extends IGenericDAO {

	void create(SuggestionEntity obj);

	SuggestionEntity read(String id);

	void update(String id, SuggestionEntity obj);

	void delete(String id);
}

package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.entity.SuggestionEntity;

public interface ISuggestionDAO {

	void create(SuggestionEntity obj);

	SuggestionEntity read(String id);

	void update(String id, SuggestionEntity obj);

	void delete(String id);
}

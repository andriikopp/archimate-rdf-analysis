package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.dao.api.generic.IGenericDAO;
import ua.khpi.apparchi.entity.StructureEntity;
import ua.khpi.apparchi.entity.api.IGenericEntity;

public interface IStructureDAO extends IGenericDAO {

	void create(IGenericEntity obj);

	StructureEntity read(String id);

	void update(String id, IGenericEntity obj);

	void delete(String id);
}

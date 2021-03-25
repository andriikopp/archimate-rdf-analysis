package ua.khpi.apparchi.dao.api;

import ua.khpi.apparchi.entity.StructureEntity;

public interface IStructureDAO extends IGenericDAO {

	void create(StructureEntity obj);

	StructureEntity read(String id);

	void update(String id, StructureEntity obj);

	void delete(String id);
}

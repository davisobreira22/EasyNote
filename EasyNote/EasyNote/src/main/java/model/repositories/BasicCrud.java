package model.repositories;

public interface BasicCrud {
		
		Object create(Object object);
		Object update(Object object);
		void delete(Long id);
		Object findById(Long id);
		
}

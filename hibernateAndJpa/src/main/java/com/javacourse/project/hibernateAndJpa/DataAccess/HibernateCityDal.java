package com.javacourse.project.hibernateAndJpa.DataAccess;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.javacourse.project.hibernateAndJpa.Entities.City;
@Repository
public class HibernateCityDal implements ICityDal{
//JPA hibernate kullnmayı daha kolay ve kısa hale getirir.
	
	private EntityManager entityManager;//EntityManager JPA dan gelen sessiona denk gelen class

	@Autowired //SessionFactory i enjekte ediyor. yazmıyoruz
	public HibernateCityDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional //AOP yi sağlar (AOP->Aspect Oriented Programming(->Görünüşe Yönelik Programlama).Build edildiğinde metodun başında ve sonunda Transactional var gibi çalışır.   )
	public List<City> getAll() {
		Session session = entityManager.unwrap(Session.class); //hibernate bir session oluşturduk
		List<City> cities = session.createQuery("from City",City.class).getResultList();
		return cities;
	}

	@Override
	public void add(City city) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(city);
	}

	@Override
	public void update(City city) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(city);
	}

	@Override
	public void delete(City city) {
		Session session = entityManager.unwrap(Session.class);
		City cityToDelete = session.get(City.class, city.getId());
		session.delete(cityToDelete);
	}

	@Override
	public City getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		City city = session.get(City.class, id);
		return city;
	}

}

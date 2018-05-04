package com.jsm.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jsm.service.exceptions.ObjectNotFoundException;

public abstract class ServiceImpl<T, RE extends JpaRepository<T, Long>> implements Service<T> {

	@Autowired
	private RE repository;
	
	
	
	@Override
	public T save(T object) {
		object = repository.save(object);
		return object;
	}

	@Override
	public Optional<T> findById(Long id) {
		//System.out.println("Passou Aqui");
		Optional<T> t = repository.findById(id);
		//System.out.println(t);
		if(!t.isPresent()) {
			//System.out.println("Passou Aqui 2");
			throw new ObjectNotFoundException("Objeto não encontrado com ID: "+id);
		}
		return t;
	}
	
	@Override
	public T getOne(Long id) {		
		return findById(id).get();
	}

	@Override
	public List<T> findAll() {		
		return repository.findAll();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {		
		return repository.findAll(pageable);
	}

	@Override
	public T update(Long id,T object) {
		T saved = findById(id).get();
		BeanUtils.copyProperties(object, saved, "id");
		saved = repository.save(saved);
		return saved;
	}

	@Override
	public void deleteById(Long id) {
		System.out.println(id);
		repository.findById(id);
		repository.deleteById(id);

	}

	
	@Override
	public void delete(T object) {
		Long id = getId(object);
		System.out.println(id);
		repository.findById(id);
		repository.delete(object);

	}

	public Long getId(T object) {
		Long id = -1L;
		try {
			Method m = object.getClass().getMethod("getId");
			m.setAccessible(true);
			id = (Long)m.invoke(object);
		} catch (NoSuchMethodException e) {			
			e.printStackTrace();
		} catch (SecurityException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}
		
		return id;
	}

	public RE getRepository() {
		return repository;
	}
	
	

}

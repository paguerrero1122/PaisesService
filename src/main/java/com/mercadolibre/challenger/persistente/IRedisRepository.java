package com.mercadolibre.challenger.persistente;

import java.util.Map;
import java.util.Set;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRedisRepository<T , ID> {

	public void saveHash(String key , T object , ID atributo);
	
	public Map<String,T> findAllHash(String key);
	
	public void saveListHash(String key , Map<String, T> hashes);
	
	public T findHashforKey(String key ,String atributo);
	
	public void saveOnSet(String key , Object value , double score);
	
	public void removeElementSet(String key , Object value);

	public Set<T> getObjectbyScrore(String key , double score);
	
	public Set<T> getSetRange(String key , int objectInicial , int objectFinal) ;
	
}

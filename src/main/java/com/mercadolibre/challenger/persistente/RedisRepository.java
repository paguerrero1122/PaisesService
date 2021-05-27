package com.mercadolibre.challenger.persistente;

import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository<T,ID> implements IRedisRepository<T, ID> {
	
	private HashOperations<String, String, T> hashOperations;
	private ZSetOperations<String, Object> zSetOperations;
	
	public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
		hashOperations = redisTemplate.opsForHash();
		zSetOperations = redisTemplate.opsForZSet();
	}
	
	@Override
	public void saveHash(String key , T object , ID atributo){
		hashOperations.put(key, atributo.toString() , object);
	}

	@Override
	public Map<String, T> findAllHash(String key) {
		return hashOperations.entries(key);
	}
	
	@Override
	public void saveListHash(String key , Map<String, T> hashes) {
		hashOperations.putAll(key.toString(), hashes);
	}
	
	@Override
	public T findHashforKey(String key ,String atributo) {
		T result = hashOperations.get(key, atributo);
		return result;
	}
	
	@Override
	public void saveOnSet(String key , Object value , double score) {
		zSetOperations.add(key, value, score);
	}
	
	@Override
	public void removeElementSet(String key , Object value) {
		zSetOperations.remove(key, value);
		
	}
	
	@Override
	public Set<T> getObjectbyScrore(String key , double score){
		return (Set<T>)zSetOperations.rangeByScore(key,score, score);
	}
	
	@Override
	public Set<T> getSetRange(String key , int objectInicial , int objectFinal) {
		return (Set<T>)zSetOperations.range(key, objectInicial, objectFinal);
	}

}

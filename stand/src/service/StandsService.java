package service;

import java.util.List;

import pojo.stands;

public interface StandsService {
	public List<stands> selectAll(String name,int num,int pageSize);
	public int count(String name);
	public int insert(stands s);
	public stands selectId(int id);
	public int update(stands s);
	public boolean delete(int id);

}

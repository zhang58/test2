package service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mapper.StandsMapper;
import pojo.stands;
import service.StandsService;
@Service("standsService")
public class StandsServiceImpl implements StandsService{

	@Resource
	private StandsMapper standsMapper;
	@Override
	public List<stands> selectAll(String name, int num, int pageSize) {
		return standsMapper.selectAll(name, num, pageSize);
	}

	@Override
	public int count(String name) {
		return standsMapper.count(name);
	}

	@Override
	public int insert(stands s) {
		try {
			int count=standsMapper.insert(s);
			if(count>0){
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public stands selectId(int id) {
		return standsMapper.selectId(id);
	}

	@Override
	public int update(stands s) {
		return standsMapper.update(s);
	}

	@Override
	public boolean delete(int id) {
		try {
			int count=standsMapper.delete(id);
			if(count>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

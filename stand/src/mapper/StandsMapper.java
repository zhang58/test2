package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import pojo.stands;

@Repository("standsMapper")
public interface StandsMapper {
	public List<stands> selectAll(@Param("name")String name,@Param("num")int num,@Param("pageSize")int pageSize);
	public int count(@Param("name")String name);
	public int insert(stands s);
	public stands selectId(@Param("id")int id);
	public int update(stands s);
	public int delete(@Param("id")int id);

}

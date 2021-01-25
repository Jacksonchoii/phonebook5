package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository /* 설정값 어노테이션 확인 */
public class PhoneDao {

	@Autowired //자동 연결
	private SqlSession sqlSession;
	
	//전체 리스트 가져오기
	public List<PersonVo> getPersonList(){
		System.out.println("dao: getPersonList()");
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList2"); /*phonebook.xml에 작성하는 쿼리문의 이름*/
		
		System.out.println(personList.toString());
		return personList;
	}

	
	//전화번호 저장
	public int personInsert(PersonVo personVo) {
		System.out.println(personVo.toString()+" + test");
		int count = sqlSession.insert("phonebook.insert", personVo);
		
		return count;
	}
	
	
	//전화번호 삭제
	public int personDelete(int personId) {
		System.out.println("dao: personDelete()" + personId);
		
		int count = sqlSession.delete("phonebook.delete", personId);  //이름정해주고 넘겨줄 데이터값 지정
		System.out.println(count);
		
		return count;
	}
	
	
	//사람 1명 데이터 가져오기
	public PersonVo getPerson(int personId) {
		System.out.println("dao: getPerson()" + personId);
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectOne", personId);
		System.out.println(personVo.toString());
		
		return personVo;
	}
	
	//사람 1명 데이터 가져오기 2 -- Map으로 받기
	public Map<String, Object> getPerson2(int personId) {
		System.out.println("dao: getPerson2()" + personId);
		
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectOne2", personId);
		System.out.println(personMap.toString());
		
		/*
		String name = (String)personMap.get("name");
		System.out.println(name);
		
		int id = Integer.parseInt(String.valueOf(personMap.get("personId")));
		System.out.println(id);
		*/
		
		return personMap;
	}
	
	//전화번호 수정
	public int personUpdate(PersonVo personVo) {
		System.out.println("dao: personUpdate()");
		
		int count = sqlSession.update("phonebook.update", personVo);
		System.out.println("dao: personUpdate()" + count);
		return count;
	}
	
	//수정2 Map을 어떻게, 언제 쓸 것인가에 대해 생각해보기
	public int personUpdate2(int personId, String name, String hp, String company) {
		System.out.println("dao: personUpdate()"+ personId +", " + hp +", " + company);
		
		//vo대신 --> map 이용
		//vo --> PersonVo personVo = new PersonVo(personId, name, hp, company);
		Map<String, Object> personMap = new HashMap<String, Object>(); //Map만 만든거고 //tag달 이름, 실제로 들어올 데이터 -->(String뿐만 아니라 여러가지 데이터가 들어와야해서 Object)
		personMap.put("id", personId); //("" key=이름 항상 String이어야함)
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		System.out.println(personMap.toString());
		
		return sqlSession.update("phonebook.update2", personMap); //굳이 변수 만들지 않고 어차피 count값이기 때문에 줄여쓰기
	}
	
}

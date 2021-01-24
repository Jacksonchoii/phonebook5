package com.javaex.dao;

import java.util.List;

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
	
	
	//전화번호 수정
	public void personUpdate(PersonVo personVo) {
		System.out.println("dao: personUpdate()");
		
		sqlSession.update("phonebook.update", personVo);
	}
	
	
}

package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.MemberVo;


@Service
public class GuestbookService {
	@Autowired
	private GuestDao guestDao;

	// 등록
	public int exeWrite(MemberVo memberVo) {
		System.out.println("GuestbookService.exeWrite()");

		// PhonebookDao phonebookDao = new PhonebookDao();

		int count = guestDao.memberInsert(memberVo);

		return count;
	}

	// 리스트
	public List<MemberVo> exeList() {
		System.out.println("PhonebookService.exeList()");

		// PhonebookDao phonebookDao = new PhonebookDao();

		List<MemberVo> memberList = guestDao.memberSelect();

		return memberList;
	}

	public int exeDelete(int no, String password) {
		System.out.println("PhonebookService.exeDelete()");

		// PhonebookDao phonebookDao = new PhonebookDao();

		int memberVo = guestDao.memberDelete(no, password);

		return memberVo;
	}

	public MemberVo exedForm(int no) {


		MemberVo memberVo = guestDao.memberSelectOne(no);

		return memberVo;
	}

}

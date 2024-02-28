package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.MemberVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	// 등록2
	// localhost:8080/phonebook5/phone/write?name=황일영&hp=010&company=02
	@RequestMapping(value = "/guest/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute MemberVo memberVo) {

		System.out.println("GuestbookController.write");

		guestbookService.exeWrite(memberVo);

		return "redirect:/guest/addList";

	}

	// 리스트,등록폼
	@RequestMapping(value = "/guest/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("guestbookController.addList()");

		List<MemberVo> memberList = guestbookService.exeList();

		model.addAttribute("mList", memberList);

		return "addList";
	}

	// 삭제
	@RequestMapping(value = "/guest/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam(value = "no") int no, @RequestParam(value = "password") String password) {

		System.out.println("guestbookController.delete()");

		System.out.println(no);

		guestbookService.exeDelete(no, password);

		return "redirect:/guest/addList";

	}

	// 삭제폼
	@RequestMapping(value = "/guest/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@RequestParam(value = "no") int no, Model model) {

		System.out.println("guestbookController.deleteform()");

		MemberVo memberVo = guestbookService.exedForm(no);

		model.addAttribute("memberVo", memberVo);

		return "deleteForm";
	}
}

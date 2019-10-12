package com.develop.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.model.TestModel;
import com.develop.model.User;
import com.develop.service.IUserInfoMsg;

@RestController
@RequestMapping(value = "/test")
public class TestRestController {

	@Autowired
	IUserInfoMsg userInfoMsg;

	@RequestMapping("/testcontroller")
	public List<TestModel> test(User user) {

		return userInfoMsg.test(user);

	}

}

package com.develop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.dao.UserInfoDao;
import com.develop.model.RestBody;
import com.develop.model.TestModel;
import com.develop.model.User;
import com.develop.service.IUserInfoMsg;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class UserInfoMgrImpl implements IUserInfoMsg {

	@Autowired
	UserInfoDao userinfoDao;

	@Override
	public List<User> queryUsersByName(String username) {

		return userinfoDao.queryUsersByName(username);
	}

	@Override
	public String checkUserAuth(User user) {

		User users = userinfoDao.checkUserAuth(user);

		if ("管理员".equals(users.getRolename())) {
			// 管理员
			return "2";
		} else if (users == null || StringUtils.isBlank(users.getId())) {

			// 查无此人
			return "3";
		}

		// 普通用户
		return "1";
	}

	@Override
	public int addUser(User user) {
		return userinfoDao.addUser(user);

	}

	/**
	 * 删除
	 */
	@Override
	public RestBody deleteUserById(String userIds) {
		JSONArray array = JSONArray.fromObject(userIds);

		RestBody restBody = new RestBody();

		Map<String, List<String>> delResult = new HashMap<String, List<String>>();

		// 成功集合
		List<String> listSuc = new ArrayList<String>();

		// 失败集合
		List<String> listFail = new ArrayList<String>();

		for (int i = 0; i < array.size(); i++) {

			Object o = array.get(i);
			JSONObject jsonTemp = JSONObject.fromObject(o);
			User user = (User) JSONObject.toBean(jsonTemp, User.class);

			// 删除用户
			int delRes = userinfoDao.deleteUserById(user.getId());

			if (delRes == 1) {
				listSuc.add(user.getUsername());
			} else {
				listFail.add(user.getUsername());
			}
		}

		delResult.put("delSuccess", listSuc); // 删除失败
		delResult.put("delFail", listFail);// 删除成功

		restBody.setBody(delResult);
		restBody.setCode(1);
		restBody.setMsg("删除成功");
		return restBody;
	}

	@Override
	public List<TestModel> test(User user) {

		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("id", user.getId());
		obj.put("username", user.getUsername());

		return userinfoDao.test(user.getId());
	}

}

package com.itsci.fingerprint.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.LoginManager;
import com.itsci.fingerprint.model.Login;
import com.itsci.fingerprint.model.Person;

@RestController
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Login VerifyLogin(@RequestBody String j) throws SQLException, JSONException, IOException {

		LoginManager mng = new LoginManager();
		System.out.println("Json : " + j);

		JSONObject json = new JSONObject(j);
		String username = json.getString("username");
		String password = json.getString("password");

		List<Login> login = mng.searchLogin(username, password);

		if (login.size() != 0) {
			System.out.println("Login Success");
			return login.get(0);
		}
		
		System.out.println("Invalid login");
		Login invalid = new Login();
		return invalid;
	}
}

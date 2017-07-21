package com.itsci.fingerprint.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.AnnouceNewsManager;
import com.itsci.fingerprint.model.Schedule;

@RestController
public class AnnouceNewsController {

	@RequestMapping(value = "/annouceNews", method = RequestMethod.POST)
	public List<String> VerifyLogin(@RequestBody String j) throws SQLException, JSONException, IOException {

		AnnouceNewsManager mng = new AnnouceNewsManager();
		System.out.println("Json : " + j);

		JSONObject json = new JSONObject(j);
		long periodID = json.getLong("periodID");

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String today = sdf.format(d);
		System.out.println("TODAY " + today);

		List<Schedule> list = mng.searchScheduleDate(periodID);

		List<String> listDate = new ArrayList<>();
		for (Schedule i : list) {
			String[] sp = i.getScheduleDate().toString().split(" ");
			try {
				Date date = sdf.parse(sp[0]);
				if (date.after(d)) {
					listDate.add(sp[0]);
					System.out.println("ADD " + sp[0]);
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}

		}
		return listDate;
	}
}

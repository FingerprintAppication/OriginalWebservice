package com.itsci.fingerprint.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.AnnouceNewsManager;
import com.itsci.fingerprint.manager.SectionManager;
import com.itsci.fingerprint.manager.ViewListSubjectManager;
import com.itsci.fingerprint.model.AnnouceNews;
import com.itsci.fingerprint.model.Section;
import com.itsci.fingerprint.model.Subject;

@RestController
public class ViewAnnounceNews {
	ViewListSubjectManager vlsm = new ViewListSubjectManager();
	AnnouceNewsManager anm = new AnnouceNewsManager();
	SectionManager sm = new SectionManager();

	@RequestMapping(value = "/viewAnnouceNews", method = RequestMethod.GET)
	public List<AnnouceNews> viewAnnouceNews(@RequestParam(value = "studentId") Long id) {
		List<AnnouceNews> listAnnouce = new ArrayList<AnnouceNews>();

		List<Subject> list = vlsm.searchStudentSubject(id);
		Map<String, Subject> map = new HashMap<String, Subject>();
		for (Subject i : list)
			map.put(i.getSubjectName(), i);

		List<AnnouceNews> announce = anm.getAnnounceNews();
		for (AnnouceNews a : announce) {
			Section s = sm.searchSectionByPeriod(a.getSchedule().getPeriod().getPeriodID());
			if (map.get(s.getSubject().getSubjectName()) != null) {
				a.getSchedule().getPeriod().setSection(s);
				a.getSchedule().setPostpone(null);
				a.getSchedule().getPeriod().getSection().setPeriodList(null);
				a.getSchedule().getPeriod().setScheduleList(null);
				listAnnouce.add(a);

			}
		}
		System.out.println("LIST ANANNANAAN " + listAnnouce.get(0).toString());
		return listAnnouce;
	}
}

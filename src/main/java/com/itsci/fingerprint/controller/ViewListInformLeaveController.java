package com.itsci.fingerprint.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.SectionManager;
import com.itsci.fingerprint.manager.ViewListInformLeaveManager;
import com.itsci.fingerprint.manager.ViewListSubjectManager;
import com.itsci.fingerprint.model.InformLeave;
import com.itsci.fingerprint.model.Section;
import com.itsci.fingerprint.model.Subject;

@RestController
public class ViewListInformLeaveController {
	ViewListSubjectManager mng = new ViewListSubjectManager();
	ViewListInformLeaveManager vimg = new ViewListInformLeaveManager();
	SectionManager sm = new SectionManager();

	@RequestMapping(value = "/listinformleave", method = RequestMethod.GET)
	public List<InformLeave>/* List<InformLeave> */ getListInformLeave(@RequestParam(value = "id") String personId) {
		Long id = Long.parseLong(personId);
		// String typePerson = mng.checkPersonTeacher(id);

		List<Subject> listSubject = new ArrayList<>();
		String teacherID = mng.searchTeacherID(id);
		listSubject = mng.searchTeacherSubject(teacherID);
		List<InformLeave> listInform = vimg.searchListInformLeave();
		List<InformLeave> newList = new ArrayList<>();
		for (InformLeave i : listInform) {
			i.getSchedule().setPostpone(null);
			i.getSchedule().getPeriod().setScheduleList(null);
			i.getStudent().setFingerprintData(null);
			i.getSchedule().getPeriod().setRoom(null);
		}
		System.out.println(listInform.size() + " size inform");
		for (Subject s : listSubject) {

			for (InformLeave l : listInform) {
				Section section = sm.searchSectionByPeriod(l.getSchedule().getPeriod().getPeriodID());
				if (section.getSubject().getSubjectName().equals(s.getSubjectName())) {
					l.getSchedule().getPeriod().setSection(section);
					l.getSchedule().getPeriod().getSection().setPeriodList(null);
					if (!"".equals(l.getSupportDocument())) {
						try {
							File file = new File("C:/informleave/"+l.getSchedule().getPeriod().getSection()
									.getSubject().getSubjectNumber()+"/"+l.getSupportDocument());
							InputStream is = new FileInputStream(file);
							BufferedImage img = ImageIO.read(is);
							ByteArrayOutputStream bao = new ByteArrayOutputStream();
							ImageIO.write(img, "png", bao);
							String en64 = Base64.encodeBase64String(bao.toByteArray());
							l.setSupportDocument(en64);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					newList.add(l);
					System.out.println(l.getSchedule().getScheduleDate() + " Date@");
				}

			}

		}
		return newList/* listInform */;
	}

}

package com.itsci.fingerprint.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.InformLeaveManager;
import com.itsci.fingerprint.manager.ViewLeaveHistoryManager;
import com.itsci.fingerprint.model.Base64Model;
import com.itsci.fingerprint.model.InformLeave;
import com.itsci.fingerprint.model.Section;

import Decoder.BASE64Decoder;

@RestController
public class ViewLeaveHistoryController {
	ViewLeaveHistoryManager mng = new ViewLeaveHistoryManager();
	

	@RequestMapping(value = "/leaveHistory", method = RequestMethod.POST)
	public List<InformLeave> searchLeaveHistory(@RequestBody String j) throws SQLException, JSONException, IOException {

		System.out.println("JSON : " + j.toString());

		JSONObject json = new JSONObject(j);
		long personID = json.getLong("personID");

		List<InformLeave> list = new ArrayList<>();
		list = mng.searchInformLeave(personID);
		System.out.println(list.get(0).getSchedule().getScheduleDate()+" date from controller!");
		for (InformLeave i : list) {
			i.getSchedule().getPeriod().setScheduleList(null);
			i.getSchedule().getPeriod().getRoom().setFingerprintScannerList(null);
			i.getStudent().setFingerprintData(null);
			Section section = mng.searchSection(i.getSchedule().getPeriod().getPeriodID());
			section.setPeriodList(null);
			
			i.getSchedule().getPeriod().setSection(section);
			System.out.println(i.toString());
			if (!"".equals(i.getSupportDocument()) || !(null ==i.getSupportDocument()) ) {
				try {
					File file = new File("C:/informleave/"+i.getSchedule().getPeriod().getSection()
							.getSubject().getSubjectNumber()+"/"+i.getSupportDocument());
					InputStream is = new FileInputStream(file);
					BufferedImage img = ImageIO.read(is);
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					ImageIO.write(img, "png", bao);
					String en64 = Base64.encodeBase64String(bao.toByteArray());
					i.setSupportDocument(en64);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	@RequestMapping(value = "/updateImageLeaveHistory", method = RequestMethod.POST)
	public String updateImageLeaveHistory (@RequestBody InformLeave inform) throws IOException{
		System.out.println("already send JSON! "+inform.getSchedule().getScheduleDate());
		InformLeave informLeave = mng.searchInformLeaveByInformID(inform.getInformLeaveID());
		Calendar cal = Calendar.getInstance();
		cal.setTime(informLeave.getSchedule().getScheduleDate());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String date = year  + "-" + month + "-" + day;
		System.out.println("date " + date);
		if(inform.getSupportDocument()!=""){

			String subjecFolder = inform.getSchedule().getPeriod().getSection().getSubject().getSubjectNumber();
			String nameImageToSave = inform.getStudent().getStudentID()+"#"+subjecFolder+"#"+date+".png";
			File createFile = new File("C://informleave//"+subjecFolder);
			if(!createFile.exists()){
				createFile.mkdirs();
				createFile.getParentFile().createNewFile();
			}
			BufferedImage imgs = Base64Model.decodeToImage(inform.getSupportDocument());
			File tmp = new File(createFile.getPath()+"//"+nameImageToSave);
			ImageIO.write(imgs, "png", tmp);
			informLeave.setSupportDocument(nameImageToSave);
			informLeave.setStatus("รอ");
			return mng.updateInformLeave(informLeave);
		}
		return "not success";
	}
	
	

}

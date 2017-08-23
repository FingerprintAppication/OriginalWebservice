package com.itsci.fingerprint.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.InformLeaveManager;
import com.itsci.fingerprint.manager.ScheduleManager;
import com.itsci.fingerprint.manager.SectionManager;
import com.itsci.fingerprint.manager.StudentManager;
import com.itsci.fingerprint.model.InformLeave;
import com.itsci.fingerprint.model.Schedule;
import com.itsci.fingerprint.model.Section;
import com.itsci.fingerprint.model.Student;

import Decoder.BASE64Decoder;

@RestController
public class ViewInformLeaveController {
	StudentManager stm;
	ScheduleManager scm;
	SectionManager sm;
	InformLeaveManager inMa;
	String result = "";
	
	@RequestMapping(value="/informleave",method = RequestMethod.POST)
	public String getInformLeave (@RequestBody InformLeave inform) throws IOException {
		stm = new StudentManager();
		scm = new ScheduleManager(); 
		sm = new SectionManager();
		inMa = new InformLeaveManager();
		System.out.println("LONGID "+inform.getInformLeaveID());
		/*********GET DATE TO CALENDAR*********/
		Calendar cal = Calendar.getInstance();
	    cal.setTime(inform.getSchedule().getScheduleDate());
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    String date = year+"-"+month+"-"+day;
	    System.out.println("Date "+date);
	    /*********DECLARE VARIABLE FOR SEARCH*********/
	    Long getStudentId = inform.getStudent().getStudentID();
	    Long getPeriodId = inform.getSchedule().getPeriod().getPeriodID();
		Student student = stm.searchStudent(getStudentId);
		Schedule scc = scm.searchScheduleByDate(date,getPeriodId);
		Section section = sm.searchSectionByPeriod(getPeriodId);
		inform.setSchedule(scc);
		inform.setStudent(student);
		System.out.println(inform.getStudent().getStudentID()+ " STUID nhaa");
		
		if("ลากิจ".equals(inform.getInformType())){
			result = inMa.insertInformLeave(inform);
		}else{
			/*********CREATE FOLDER AND SAVE IMAGE*********/
			String image = inform.getSupportDocument();
			if(image!=""){
				String subjecFolder = section.getSubject().getSubjectNumber();
				String nameImageToSave = student.getStudentID()+"#"+subjecFolder+"#"+date+".png";
				File createFile = new File("C://informleave//"+subjecFolder);
				if(!createFile.exists()){
					createFile.mkdirs();
					createFile.getParentFile().createNewFile();
				}
				BufferedImage imgs = decodeToImage(image);
				File tmp = new File(createFile.getPath()+"//"+nameImageToSave);
				ImageIO.write(imgs, "png", tmp);
				inform.setSupportDocument(nameImageToSave);
			}
			/*********INSERT INFORMLEAVE*********/
			result = inMa.insertInformLeave(inform);
			System.out.println(result);
		}
		
		
		if("success".equals(result)){
			result = "ลาเรียนสำเร็จ";
		}else{
			result = "ไม่สามารถลาเรียนได้เนื่องจากท่านได้ลาเรียนวันนี้เเล้ว";
		}
		
		return result;
	}
	
	public static BufferedImage decodeToImage(String imageString) {

		BufferedImage image = null;
		byte[] imageByte;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	@RequestMapping(value="/informleavess",method = RequestMethod.GET)
	public String testBugs () throws IOException {
		InformLeave inform = new InformLeave();
		inform.setInformType("ลากิจ");
		inform.setStatus("รอ");
		inform.setSupportDocument("xxx");
		//inform.setInformLeaveID(123);
		//long aaa = 1234;
		//inform.setInformLeaveID(aaa);
		Schedule sss = new Schedule();
		sss.setScheduleID(1403);
		inform.setSchedule(sss);
		//inform.setInformType("okkk");
		Student ddd =new Student();
		ddd.setPersonID(3433);
		
		inform.setStudent(ddd);
		inMa = new InformLeaveManager();
		result = inMa.insertInformLeave(inform);
		return result;
	}

}

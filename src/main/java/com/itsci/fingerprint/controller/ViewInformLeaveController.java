package com.itsci.fingerprint.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
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
	StudentManager stm = new StudentManager();;
	ScheduleManager scm = new ScheduleManager();
	SectionManager sm = new SectionManager();
	InformLeaveManager inMa = new InformLeaveManager();
	String result = "";
	
	@RequestMapping(value="/informleave",method = RequestMethod.POST)
	public String getInformLeave (@RequestBody InformLeave inform) throws IOException {
		/********* GET DATE TO CALENDAR *********/
		Calendar cal = Calendar.getInstance();
		cal.setTime(inform.getSchedule().getScheduleDate());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String date = year  + "-" + month + "-" + day;
		System.out.println("date " + date);
		
	    /*********DECLARE VARIABLE FOR SEARCH*********/
	    Long getStudentId = inform.getStudent().getStudentID();
	    Long getPeriodId = inform.getSchedule().getPeriod().getPeriodID();
		Student student = stm.searchStudent(getStudentId);
		Schedule scc = scm.searchScheduleByDate(date,getPeriodId);
		Section section = sm.searchSectionByPeriod(getPeriodId);
		/*********check duplicated*********/
		List<InformLeave> duplicated = inMa.searchDuplicateInformLeave(""+student.getPersonID(),""+scc.getScheduleID());
		if(duplicated.size()!=0){
			return "ไม่สามารถลาเรียนได้เนื่องจากท่านได้ลาเรียนวันนี้เเล้ว";
		}
		inform.setSchedule(scc);
		inform.setStudent(student);
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

}

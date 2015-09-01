package box.action;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TakePictureAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		PropertiesConfiguration config = new PropertiesConfiguration("box/config.properties");

		Process p = Runtime.getRuntime().exec("sudo ffmpeg -i " + config.getString("URLCAMERA") + " -vframes 1 -an -ss 1 " + config.getString("PATHAUDIOFILE") + "img.jpg");
		p.waitFor();

		// return an application file instead of html page
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=img_" + (new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())) + ".jpg");

		try {

			File file = new File(config.getString("PATHAUDIOFILE") + "img.jpg");
			
//			File file = new File("C:\\Users\\Mauro\\Desktop\\img.jpg");
			
			FileInputStream in = new FileInputStream(file);
			
			ServletOutputStream out = response.getOutputStream();

			byte[] outputByte = new byte[4096];
			while (in.read(outputByte, 0, 4096) != -1) {
				out.write(outputByte, 0, 4096);
			}
			in.close();
			out.flush();
			out.close();

			if(file.exists()){
				file.delete();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

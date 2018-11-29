package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pojo.Page;
import pojo.stands;
import service.StandsService;

@Controller
public class StandsController {
	
	@Resource
	private StandsService standsService;
	
	@RequestMapping("/selectAll")
	public String selectAll(
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="pageIndex",required=false,defaultValue="1")int pageIndex,
			HttpServletRequest request){
		try {
			int count=standsService.count(name);
			Page page=new Page();
			page.setTotalCount(count);
			page.setCurrenPageNo(pageIndex);
			int num=(page.getCurrenPageNo()-1)*page.getPageSize();
			List<stands> stanList=standsService.selectAll(name, num, page.getPageSize());
			request.setAttribute("count", count);
			request.setAttribute("name", name);
			request.setAttribute("stanList", stanList);
			request.setAttribute("page", page);
			return "index";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(
			Model model,
			stands s,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value="a_path",required=false)MultipartFile attach){
		
			if(!attach.isEmpty()){
				String name=attach.getOriginalFilename();
				
				String paths=session.getServletContext().getRealPath("upload");
				
				String prefix=FilenameUtils.getExtension(name);
				
				int fileSize=500000;
				
				if(attach.getSize()>fileSize){
					
					model.addAttribute("uploadFileError", "上传文件大小不能超过500kb");
					
					return "redirect:/selectAll";
				
				}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") ||prefix.equalsIgnoreCase("jpeg") ||prefix.equalsIgnoreCase("pneg")){
					
					String fileName=System.currentTimeMillis()+new Random().nextInt(1000000)+"."+prefix;
					
					File targetFile=new File(paths,fileName);
					
					if(!targetFile.exists()){
						
						targetFile.mkdirs();
					}
					
					name=fileName;
					
					try {
						attach.transferTo(targetFile);
						s.setPath(name);
						standsService.insert(s);
						model.addAttribute(s);
						model.addAttribute("uploadFileError", "上传成功");
						return "redirect:/selectAll";
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("uploadFileError", "上传失败");
						return "redirect:/selectAll";
					}
				}
			}
				return null;
	}
	
	@RequestMapping("/selectId")
	public String selectId(@RequestParam(value="id",required=false)Integer id,
			Model model){
		try {
			stands s=standsService.selectId(id);
			model.addAttribute("s", s);
			model.addAttribute("id", id);
			return "update";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/selectAll";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(stands s,
			HttpSession session,
			Model model,
			@RequestParam(value="path",required=false)String path,
			@RequestParam(value="a_path",required=false)MultipartFile attach) throws IllegalStateException, IOException{
		if(!attach.isEmpty()){
			String name=attach.getOriginalFilename();
			String paths=session.getServletContext().getRealPath("upload");
		    String prefix=FilenameUtils.getExtension(name);
		    int fileSize=500000;
		    if(attach.getSize()>fileSize){
		    	 model.addAttribute("uploadFileError","上传大小不能超过500kB");
				  return "redirect:/selectAll";
		    }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
		    	String fileName=System.currentTimeMillis()+new Random().nextInt(1000000)+"."+prefix;
		    	File targetFile =new File(paths,fileName);
		    	if(!targetFile.exists()){
		    		targetFile.mkdirs();
		    	}
		    	name=fileName;
		    	attach.transferTo(targetFile);
		    	s.setPath(name);
		    }
		}else{
			s.setPath(path);
		}
		try {
			standsService.update(s);
			model.addAttribute("s", s);
			return "redirect:/selectAll";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/deleteId")
	public String deleteId(@RequestParam(value="id",required=false)Integer id){
		try {
			boolean falg=standsService.delete(id);
			if(falg==true){
				return "true";
			}
			return "false";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}

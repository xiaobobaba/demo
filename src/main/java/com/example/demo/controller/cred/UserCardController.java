package com.example.demo.controller.cred;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.cred.UserCardEntity;
import com.example.demo.service.cred.UserCardService;
import com.example.demo.util.ReturnParam;
import com.example.demo.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/userCard")
public class UserCardController {
	

	@Autowired
	UserCardService userCardService;
	
	@Value("${spring.servlet.multipart.location}")
	private String url;
	
	@RequestMapping(value="/findUserCardById")
	public ReturnParam<UserCardEntity> findUserCardById(@RequestBody UserCardEntity userCard){
		ReturnParam<UserCardEntity> param = new ReturnParam<UserCardEntity>();
		try {
			if(StringUtil.isNull(userCard.getCardId()+"") && StringUtil.isNull(userCard.getUserId()+"")) {
				param.setIsTan("卡片ID或用户ID为空!");
				param.setSuccess(false);
				return param;
			}
			param.setList(userCardService.findUserCardById(userCard));
			param.setSuccess(true);
		} catch (Exception e) {
			param.setSuccess(false);
			param.setIsTan("操作失败");
			log.info("不好了报错了："+e);
		}
		return param;
	}
	
	/**
	 * 
	* @Title: findUserCard  
	* @Description: TODO(查询卡片)  
	* @param @param userCard
	* @param @return    参数  
	* @return ReturnParam<UserCardEntity>    返回类型  
	* @date 2019年10月12日上午10:42:24  
	* @throws
	 */
	@RequestMapping(value="/findUserCard")
	@RequiresPermissions("user:index:card")
	public ReturnParam<UserCardEntity> findUserCard(@RequestBody UserCardEntity userCard){
		ReturnParam<UserCardEntity> param = new ReturnParam<UserCardEntity>();
		try {
			param.setTotal(userCardService.findUserCardCount());
			param.setList(userCardService.findUserCard(userCard));
			param.setSuccess(true);
		} catch (Exception e) {
			param.setSuccess(false);
			param.setIsTan("操作失败");
			log.info("不好了报错了："+e);
		}
		return param;
	}
	/**
     * 
    * @Title: upload  
    * @Description: TODO(文件上传)  
    * @param @param file
    * @param @return    文件
    * @return String    返回类型  
    * @date 2019年10月01日下午7:52
    * @throws
     */
	@RequestMapping("/upload")
	public ReturnParam<String> upload(@RequestBody MultipartFile file) {
		ReturnParam<String> param = new ReturnParam<String>();
	 try {
        if (file.isEmpty()) {
        	param.setIsTan("文件为空");
        	param.setSuccess(false);
            return param;
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName =  sdf.format(date)+file.getOriginalFilename();
        File dest = new File(url +"/card/"+ fileName);
            file.transferTo(dest);
            log.info("上传成功");
            param.setIsTan("上传成功");
            param.setSuccess(true);
            param.setObj(fileName);
       } catch (IOException e) {
           log.error(e.toString(), e);
           param.setSuccess(false);
           return param;
       }
	 	return param;
    }
	@RequestMapping("/importCard")
	@RequiresPermissions("card:import")
	public void importCard(String filepath) {
		filepath="D:\\touxiang\\cardImg";
		UserCardEntity userCard = new UserCardEntity();
		userCard.setUserId((long) 37);
		userCard.setCardTitle("随便找几张凑合");
		userCard.setCommentContent("好看！");
		userCard.setCardContent("啦啦啦阿拉啦啦啦啦啦啦啦");
		try {

			File file = new File(filepath);
			if (!file.isDirectory()) {
				System.out.println("文件");
				System.out.println("path=" + file.getPath());
				System.out.println("absolutepath=" + file.getAbsolutePath());
				System.out.println("name=" + file.getName());

			} else if (file.isDirectory()) {
				System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						System.out.println("path=" + readfile.getPath());
						System.out.println("absolutepath=" + readfile.getAbsolutePath());
						System.out.println("name=" + readfile.getName());
						userCard.setCardImg("/cardImg/"+readfile.getName());
						userCard.setCardContent(StringUtils.substringBetween(readfile.getName(), "_", "."));
						userCardService.insertUsercard(userCard);
					} else if (readfile.isDirectory()) {
						importCard(filepath + "\\" + filelist[i]);
					}
				}

			}

		} catch (Exception e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
	}
}

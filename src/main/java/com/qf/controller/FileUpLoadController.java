package com.qf.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qf.baiduapi.AdvancedGeneral;
import com.qf.vo.GarbageInfoVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("fileUpAndDown")
public class FileUpLoadController {
  /*  @RequestMapping("fileUploadController")
    @ResponseBody
    public String fileUploadController(@RequestParam("file")CommonsMultipartFile file) throws Exception {
        //1.创建一个新文件
        File destFile = new File("D:\\"+ new Data().getTime() + file.getOriginalFilename());
        //2.将file中的内容通过transferTo方法保存到新文件中
        file.transferTo(destFile);
        System.out.println(destFile.getAbsolutePath());
        return "upload success";
    }*/
  @RequestMapping("testDownLoad")
  public ResponseEntity<byte[]> testDownLoad(@RequestParam(required = false,value = "fileName")String fileName) throws IOException {
      File srcFile = new File("d://"+fileName);
      if(!srcFile.exists()){
          throw new FileNotFoundException("无法找到文件"+srcFile.getAbsolutePath());
      }
      FileInputStream fis = new FileInputStream(srcFile);
      byte[] destByteArray = new byte[fis.available()];
      //?
      fis.read(destByteArray);
      //定义一个响应头
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Disposition", "attchement;filename="+fileName);
      //设置响应状态
      int status = 200;
      HttpStatus statusCode = HttpStatus.OK;

      ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(destByteArray, headers, statusCode);

      return responseEntity;
  }

    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile dropzFile, HttpServletRequest request) throws IOException {
        System.out.println("inner upload");
        Map<String, Object> result = new HashMap<String, Object>();

        //创建文件需要存储的路径
        String destPathName = request.getSession().getServletContext().getRealPath("/static/upload");
        File destPath = new File(destPathName);
        //如果目标文件夹不存在我就创建它
        if(!destPath.exists()){
            destPath.mkdirs();
        }
        //获取文件的后缀名
        String fileSuffix = dropzFile.getOriginalFilename().substring(dropzFile.getOriginalFilename().lastIndexOf("."));

        String destFileName = UUID.randomUUID()+fileSuffix;
        System.out.println(destFileName);
        File destFile = new File(destPath,destFileName);
        if(!destFile.exists()){
            destFile.createNewFile();
        }
        dropzFile.transferTo(destFile);
        result.put("status",200);
        //http://localhost:8080/xxxxx/xxxx.jpg
        result.put("filePath","http://localhost:8080/static/upload/"+destFileName);
        return result;
    }


    @RequestMapping("garbageDiscernController")
    @ResponseBody
    public Map<String, Object> garbageDiscernController(MultipartFile dropzFile, HttpServletRequest request) throws IOException {
        System.out.println("inner upload");
        Map<String, Object> result = new HashMap<String, Object>();

        //创建文件需要存储的路径
        String destPathName = request.getSession().getServletContext().getRealPath("/static/upload");
        File destPath = new File(destPathName);
        //如果目标文件夹不存在我就创建它
        if(!destPath.exists()){
            destPath.mkdirs();
        }
        //获取文件的后缀名
        String fileSuffix = dropzFile.getOriginalFilename().substring(dropzFile.getOriginalFilename().lastIndexOf("."));

        String destFileName = UUID.randomUUID()+fileSuffix;
        System.out.println(destFileName);
        File destFile = new File(destPath,destFileName);
        if(!destFile.exists()){
            destFile.createNewFile();
        }
        dropzFile.transferTo(destFile);


        String resultStr = AdvancedGeneral.advancedGeneral(destFile.getAbsolutePath());
        //调用百度接口然后获取回传值
        result.put("status",200);
        //将json字符串转对象
        Gson gson = new Gson();
        Object o = gson.fromJson(resultStr, new TypeToken<GarbageInfoVO>() {
        }.getType());
        result.put("resultObj",o);
        //http://localhost:8080/xxxxx/xxxx.jpg
        result.put("filePath","http://localhost:8080/static/upload/"+destFileName);
        return result;
    }

}

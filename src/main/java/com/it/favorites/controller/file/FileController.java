package com.it.favorites.controller.file;

import com.it.favorites.config.GlobalProperties;
import com.it.favorites.exception.AppException;
import com.it.favorites.exception.AppExceptionAreadyExists;
import com.it.favorites.exception.AppExceptionBadRequest;
import com.it.favorites.exception.HttpResult;
import com.it.favorites.model.file.FileInfo;
import com.it.favorites.service.file.FileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/file")
@EnableConfigurationProperties({GlobalProperties.class})
@Api(tags = "文件上传下载")
public class FileController {

    @Autowired
    private GlobalProperties properties;
    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping("/list")
    @ResponseBody
    public List<FileInfo> getFileList() throws Exception {
        return fileInfoService.getFileList();
    }

    @ResponseBody
    @PostMapping("/{folder}")
    @ApiOperation("文件上传")
    public HttpResult uploadFile(@PathVariable(name = "folder") String folder,
                                 @RequestParam(value = "file") MultipartFile file,
                                 HttpServletRequest request) throws Exception {
        String name = genFilename(file.getOriginalFilename());
        String path = genFilePath(folder, name);

        //保存到数据库
        FileInfo record = new FileInfo();
        record.setName(name);
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String url = "http://" + serverName + ":" + serverPort + "/file/" + folder + "/" + name;
        record.setUrl(url);
        record.setUploadTime(LocalDateTime.now());
        fileInfoService.save(record);

        try {
            File localFile = new File(path);
            if (localFile.exists()) {
                throw new AppExceptionAreadyExists("文件已经存在");
            }

            file.transferTo(localFile);
        } catch (Exception e) {
            throw new AppExceptionBadRequest("非法路径");
        }

        return new HttpResult(name);
    }

    @ApiOperation("文件下载")
    @GetMapping("/{folder}/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable(name = "folder") String folder,
                                           @PathVariable("filename") String filename) throws Exception {
        String path = genFilePath(folder, filename);
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (Exception e) {
            throw AppException.NotFound;
        }

        HttpHeaders headers = new HttpHeaders();
        String encodeFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        headers.setContentDispositionFormData("attachment", encodeFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

    private String getRootPath() {
        if(null == rootPath) {
            rootPath = Paths.get(properties.getFilePath()).toFile().toString();
        }

        return rootPath;
    }

    private String genFilename(String filename) {
        return Long.toString(System.currentTimeMillis()) + "_" + filename;
    }

    private String genFilePath(String folder, String filename) {
        String folderPath = Paths.get(getRootPath(), folder).toString();
        File file = new File(folderPath);
        if(!file.exists()) {
            file.mkdirs();
        }

        return Paths.get(folderPath, filename).toString();
    }

    private String rootPath = null;
}

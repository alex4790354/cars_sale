package com.job4j.cars.controller;

import com.job4j.cars.entity.CarsPhoto;
import com.job4j.cars.entity.FileMetaData;
import com.job4j.cars.exception.FileStorageException;
import com.job4j.cars.service.CarsPhotoService;
import com.job4j.cars.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/carsad")
public class FileUploadController extends PageController{

    /*@Autowired
    FileStorageService fileStorageService;*/

    //final
    private FileStorageService fileStorageService;
    private CarsPhotoService carsPhotoService;

    public FileUploadController(FileStorageService fileStorageService, CarsPhotoService carsPhotoService) {
        this.fileStorageService = fileStorageService;
        this.carsPhotoService = carsPhotoService;
    }

    /**
     * Controller to display the file upload form on the frontend.
     * @param model
     * @return
     */
    @GetMapping("/photo-upload")
    public String uploadFile(@RequestParam("adid") int adid, final Model model) {
        try {
            List<CarsPhoto> carsPhotos = carsPhotoService.findByAdId(adid);
            model.addAttribute("carsPhotos", carsPhotos);
            model.addAttribute("adid", adid);
        } catch (Exception e) {
            model.addAttribute("error", "Unable to get ad_id. You can't load photo.");
            model.addAttribute("adid", 0);
        }
        return "carsads/uploadPhoto";
    }


    /**
     * POST method to accept the incoming file in the application.This method is designed to accept
     * only 1 file at a time.
     * @param file
     * @param redirectAttributes
     * @return succes page
     */
    @PostMapping("/photo-upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("adid") int adid,
                             RedirectAttributes redirectAttributes, Model model) {

        try {
             FileMetaData data = fileStorageService.store(file);
             String vFileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(data.getFileName())
                    .path("/media/download/").toUriString();
             //data.setUrl(fileDownloadUrl(data.getFileName(),"/media/download/"));
             data.setUrl(vFileDownloadUrl);
             model.addAttribute("uploadedFile", data);

            List<CarsPhoto> carsPhotos = carsPhotoService.findByAdId(adid);
            model.addAttribute("carsPhotos", carsPhotos);
            model.addAttribute("adid", adid);

        } catch (FileStorageException e) {
             model.addAttribute("error", "Unable to store the file");
             model.addAttribute("adid", 0);
             return "carsads/uploadPhoto";
        }
        return "carsads/uploadPhoto";
    }


    /*
     * Controller to allow customer to download the file by passing the file name as the
     * request URL.
     * @param fileName
     * @param response
     * @return
     * @throws FileNotFoundException
     */
    /*@GetMapping("/media/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFIle(@PathVariable String fileName, final HttpServletResponse response) throws FileNotFoundException {
        FileMetaData fileData= fileStorageService.getFile(fileName);
        response.setContentType(fileData.getMime());
       return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body(fileData.getResource());
    }*/

}

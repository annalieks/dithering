package com.processor.dithering.dither;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/dither")
public class DitheringController {

    private final DitheringService ditheringService;

    @Autowired
    public DitheringController(final DitheringService ditheringService) {
        this.ditheringService = ditheringService;
    }

    @PostMapping
    public void processImage(@RequestParam("image") MultipartFile file) {
        ditheringService.processImage(file);
    }

}

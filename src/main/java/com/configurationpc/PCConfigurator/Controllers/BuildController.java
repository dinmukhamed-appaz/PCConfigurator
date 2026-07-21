package com.configurationpc.PCConfigurator.Controllers;


import com.configurationpc.PCConfigurator.Services.BuildService;
import com.configurationpc.PCConfigurator.Services.PdfGenerationService;
import com.configurationpc.PCConfigurator.models.Build;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/builds")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildController {

    private final BuildService buildService;

    private final PdfGenerationService pdfGenerationService;

    @PostMapping("")
    public Build create() {
        Build newBuild = buildService.createBuild();
        return newBuild;
    }

    @PostMapping("/{id}/components")
    public Build addComponent(@PathVariable int id, @RequestBody int componentId) {
        return buildService.addComponents(id, componentId);
    }

    @GetMapping("/{id}")
    public Build getBuildById(@PathVariable int id){
        return buildService.showBuildById(id);
    }

    @GetMapping("")
    public List<Build> getAllBuilds() {
        return buildService.showAllBuilds();
    }

    @GetMapping("/{id}/recommendations")
    public List<String> getRecommendations(@PathVariable int id) {
        return buildService.getRecommendations(id);
    }

    @DeleteMapping("/{id}")
    public Build deleteBuildById(@PathVariable int id) {
        return buildService.deleteBuildById(id);
    }

    @DeleteMapping("/{id}/component/{componentId}")
    public Build deleteComponentBuild(@PathVariable int id, @PathVariable int componentId) {
        return buildService.deleteComponentBuild(id, componentId);
    }

    @PutMapping("/{id}/component/{componentId}")
    public Build updateComponentBuild(@PathVariable int id, @PathVariable int componentId, @RequestBody int newComponentId) {
        return buildService.updateComponentBuild(id, componentId, newComponentId);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable int id) {
        byte[] pdf = pdfGenerationService.generate(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=build-" + id + ".pdf"
                )
                .body(pdf);
    }

}

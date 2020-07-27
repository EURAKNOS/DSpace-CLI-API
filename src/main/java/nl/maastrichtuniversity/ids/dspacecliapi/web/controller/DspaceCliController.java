package nl.maastrichtuniversity.ids.dspacecliapi.web.controller;

import lombok.extern.slf4j.Slf4j;
import nl.maastrichtuniversity.ids.dspacecliapi.service.DspaceCliService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequestMapping("/api")
@RestController
public class DspaceCliController {
    private DspaceCliService dspaceCliService;

    public DspaceCliController(DspaceCliService dspaceCliService) {
        this.dspaceCliService = dspaceCliService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerObjects(@RequestParam("collection-id") String collectionId,
                                                  @RequestParam("source") String source,
                                                  @RequestParam("mapfile") String mapFile)
            throws IOException, InterruptedException {
        dspaceCliService.registerObjects(collectionId, source, mapFile);
        return ResponseEntity.ok().build();
    }
}

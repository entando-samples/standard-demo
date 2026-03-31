package org.entando.demo.banking.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoBundleVersionResource {

    private final Logger log = LoggerFactory.getLogger(InfoBundleVersionResource.class);

    @Value( "${bundle.version:0.0.1}")
    private String bundleVersion;

    @GetMapping("/bundle-version")
    public ResponseEntity<String> getBundleVersion() {
        return ResponseEntity.ok(bundleVersion);
    }
}

package com.xsis.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.Variant;
import com.xsis.javapos.services.VariantService;

@RestController
@RequestMapping("/api/variant")
public class VariantApiController {
    @Autowired
    private VariantService variantSvc;
    @GetMapping("")
    public ResponseEntity<List<Variant>> get() {
        try {
            List<Variant> data = variantSvc.getAll();
            return new ResponseEntity<List<Variant>>(data, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<List<Variant>>(HttpStatus.NO_CONTENT);
        }
    }
}

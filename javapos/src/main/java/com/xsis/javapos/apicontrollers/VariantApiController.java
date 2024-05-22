package com.xsis.javapos.apicontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.Variant;
import com.xsis.javapos.services.VariantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/variant")
public class VariantApiController {
    @Autowired
    private VariantService variantSvc;
    
    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            List<Variant> data = variantSvc.getAll();
            
            if (data.size() > 0) {
                return new ResponseEntity<>(data, HttpStatus.OK);       
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Map<String, Object>> data = variantSvc.objGetAll();
            
            if (data.size() > 0) {
                return new ResponseEntity<List<Map<String, Object>>>(data, HttpStatus.OK);       
            } else {
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        try {
            List<Variant> data = variantSvc.getByName(name);

            if (data.size() > 0) {
                return new ResponseEntity<List<Variant>>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }  
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getByCatId/{categoryId}")
    public ResponseEntity<?> getByCatId(@PathVariable long categoryId) {
        try {
            List<Map<String, Object>> data = variantSvc.getByCatId(categoryId);

            if (data.size() > 0) {
                return new ResponseEntity<List<Map<String, Object>>> (data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }  
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByCatName/{categoryName}")
    public ResponseEntity<?> getByCatName(@PathVariable String categoryName) {
        try {
            List<Map<String, Object>> data = variantSvc.getByCatName(categoryName);

            if (data.size() > 0) {
                return new ResponseEntity<List<Map<String, Object>>> (data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }  
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> Create(@RequestBody final Variant data) {
        //TODO: process POST request
        try {
            Variant newVariant = variantSvc.Create(data);
            return new ResponseEntity<Variant>(newVariant, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> Update(@RequestBody final Variant data) {
        //TODO: process PUT request
        try {
            Variant updateVariant = variantSvc.Update(data);
            
            if (updateVariant.getId() > 0) {
                return new ResponseEntity<Variant>(updateVariant, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Variant does not exsist!", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/{categoryId}")
    public ResponseEntity<?> Update(@PathVariable final long id, @PathVariable long categoryId) {
        //TODO: process DELETE request
        try {
            Variant deletedVariant = variantSvc.Delete(id, categoryId);

            if (deletedVariant.getId() > 0) {
                return new ResponseEntity<Variant>(deletedVariant, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Variant does not exsist!", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}

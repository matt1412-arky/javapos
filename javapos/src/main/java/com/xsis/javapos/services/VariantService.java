package com.xsis.javapos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.xsis.javapos.models.Variant;
import com.xsis.javapos.repositories.VariantRepository;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepository;
    public List<Variant> getAll() throws Exception{
        try {
            List<Variant> data = variantRepository.findAll();
            if(data.size() > 0) {
                return data;
            } else {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Variant table has no data");
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
}

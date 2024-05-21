package com.xsis.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.javapos.models.Variant;
import com.xsis.javapos.repositories.VariantRepository;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepository;
    private Optional<Variant> variantExsist;
    public List<Variant> getAll() throws Exception{
        try {
            return variantRepository.findByIsDeleted(false).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public List<Map<String, Object[]>> objGetAll() throws Exception {
        try {
            return variantRepository.findAllNative().get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public Variant Create(Variant data) throws Exception {
        variantExsist = variantRepository.findByName(data.getName());
        if(variantExsist.isEmpty()) {
            return variantRepository.save(data);
        } else {
            return new Variant();
        }
    }

    public Variant Update(Variant data) throws Exception {
        variantExsist = variantRepository.findById(data.getId());

        if (!variantExsist.isEmpty()) {
            // Update Field
            data.setCreateBy(variantExsist.get().getCreateBy());
            data.setCreateDate(variantExsist.get().getCreateDate());
            data.setDeleted(variantExsist.get().isDeleted());
            data.setUpdateDate(LocalDateTime.now());

            // Update Table
            return variantRepository.save(data);
        } else {
            return new Variant();
        }
    }

    public Variant Delete(long id, long categoryId) throws Exception {
        variantExsist = variantRepository.findById(id);

        if (!variantExsist.isEmpty()) {
            Variant variant = variantExsist.get();

            // Update Field
            variant.setDeleted(true);
            variant.setUpdateDate(LocalDateTime.now());

            // Update Table
            return variantRepository.save(variant);
        } else {
            return new Variant();
        }
    }

    public List<Variant> getByName(String name) throws Exception {
        return variantRepository.findByNameContainsIgnoreCase(name)
            .orElseThrow(() -> new Exception("Variant table cannot be accessed!"));
    }
}

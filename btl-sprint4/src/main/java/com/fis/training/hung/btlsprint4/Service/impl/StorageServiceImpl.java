package com.fis.training.hung.btlsprint4.Service.impl;

import com.fis.training.hung.btlsprint4.Service.StorageService;
import com.fis.training.hung.btlsprint4.exception.CriminalCaseException;
import com.fis.training.hung.btlsprint4.exception.StorageException;
import com.fis.training.hung.btlsprint4.model.Evidence;
import com.fis.training.hung.btlsprint4.model.Storage;
import com.fis.training.hung.btlsprint4.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageRepository storageRepository;
    @Override
    public Storage findByStorageId(Long storageId) {
        return storageRepository.findById(storageId).orElseThrow(() -> new StorageException("Storage not found"));
    }

    @Override
    public Storage createDetective(Storage storage) {
        if(storage.getId()==null) return storageRepository.save(storage);
        Optional<Storage> optionalStorage = storageRepository.findById(storage.getId());
        if(optionalStorage.isEmpty())
            return storageRepository.save(storage);
        else throw new StorageException("Create storage fail! It already exist");
    }

    @Override
    public Storage updateStorage(Storage storage) {
        storageRepository.findById(storage.getId()).orElseThrow(() -> new StorageException("Storage not found"));
        return storageRepository.save(storage);
    }

    @Override
    public void removeStorage(Long storageId) {

    }

    @Override
    public List<Storage> findAll() {
        return null;
    }
}

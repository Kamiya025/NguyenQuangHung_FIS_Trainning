package com.fis.training.hung.btlsprint4.Service;


import com.fis.training.hung.btlsprint4.model.Storage;

import java.util.List;

public interface StorageService{
    Storage findByStorageId(Long storageId);
    Storage createDetective(Storage storage);
    Storage updateStorage(Storage storage);
    void removeStorage(Long storageId);
    List<Storage> findAll();
}

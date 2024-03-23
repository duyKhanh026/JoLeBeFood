package com.example.jolebefood.DAO;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CallFirebaseStrorage {
    FirebaseStorage storage = FirebaseStorage.getInstance();

    StorageReference storageRef;

    public CallFirebaseStrorage() {
        storageRef = storage.getReferenceFromUrl("gs://jolebefood.appspot.com");
    }

    public StorageReference getStorageRef() {
        return storageRef;
    }

    public void setStorageRef(StorageReference storageRef) {
        this.storageRef = storageRef;
    }
}

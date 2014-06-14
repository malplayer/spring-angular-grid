package com.novation.eligibility.dto.dtos;

import com.novation.eligibility.dto.general.DataTransferObject;

public class ChangePasswordRequestDto extends DataTransferObject {

    private static final long serialVersionUID = 1L;

    public String currentHash;
    public String newHash;
    public String salt;

    public String getCurrentHash() {
            return currentHash;
    }

    public void setCurrentHash(String currentHash) {
            this.currentHash = currentHash;
    }

    public String getNewHash() {
            return newHash;
    }

    public void setNewHash(String newHash) {
            this.newHash = newHash;
    }

    public String getSalt() {
            return salt;
    }

    public void setSalt(String salt) {
            this.salt = salt;
    }
}
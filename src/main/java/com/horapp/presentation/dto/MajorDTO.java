package com.horapp.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MajorDTO {
    private long idMajor;
    private String majorName;

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public long getIdMajor() {
        return idMajor;
    }

    public void setIdMajor(long idMajor) {
        this.idMajor = idMajor;
    }
}

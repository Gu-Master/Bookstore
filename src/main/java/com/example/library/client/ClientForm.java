package com.example.library.client;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ClientForm {

    @NotBlank(message = "Укажите ФИО клиента")
    @Size(max = 255, message = "ФИО не должно превышать 255 символов")
    private String fullName;

    @NotNull(message = "Укажите дату рождения")
    @Past(message = "Дата рождения должна быть в прошлом")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

package com.skypro.recipe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController  {

    @GetMapping
    public String index() {
        return "<h1 style=\"text-align:center\">Приложение запущено.</h1>"; // <h1 style="text-align:center"> ......</h1>/  это признак заголовка и размещение текста по центру
    }

    @GetMapping("/info")
    public String indfo() {
        return "Ученик: Дюшкин Владимир. </br>" +
                "Название проекта: recipe-backend-app. </br>" +
                "Дата создания проекта: 12.02.2023 </br>" +
                "Описание проекта: приложение для сайта рецептов";
    }
}

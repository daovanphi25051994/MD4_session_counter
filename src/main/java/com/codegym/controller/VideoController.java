package com.codegym.controller;

import com.codegym.model.MyCounter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("views")
public class VideoController {

    @ModelAttribute("views")
    public MyCounter setUpCounter(){
        return new MyCounter();
    }

    @GetMapping("/look-video")
    public ModelAndView watchVideoAndPlusView(@ModelAttribute("views") MyCounter myCounter){
        myCounter.increment();
        ModelAndView modelAndView = new ModelAndView("result");
        return modelAndView;
    }
}
package com.example.demo.app;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
    @RequestMapping("/")
    public ModelAndView register(Model model) {
        model.addAttribute("register", RegistrationForm.builder().build());
        return new ModelAndView("index.html");
    }

    @PostMapping("/register")
    public ModelAndView processRegisterForm(@Valid @ModelAttribute("register") final RegistrationForm form, final BindingResult result) {
        if (result.hasErrors() || isCaptchaWrong(form.getCaptcha())) {
            if (isCaptchaWrong(form.getCaptcha())) {
                result.addError(new FieldError("register", "captcha", "wrongcaptcha"));
            }
            return new ModelAndView("index.html");
        }

        ModelAndView targetView = new ModelAndView("redirect:/success");
        // targetView.addObject("initname", serviceResult.get("name"));
        return targetView;
    }

    private boolean isCaptchaWrong(String captcha) {
        return !"car".equals(captcha);
    }

    @RequestMapping("/success")
    public ModelAndView success(Model model) {
        return new ModelAndView("success.html");
    }

}

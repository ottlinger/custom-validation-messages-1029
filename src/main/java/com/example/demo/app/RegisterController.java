package com.example.demo.app;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

public class RegisterController {
    @RequestMapping("/register")
    public ModelAndView register(Model model) {
        model.addAttribute("register", RegistrationForm.builder().build());
        return new ModelAndView("index.html");
    }

    @PostMapping("/registerselfservice")
    public ModelAndView processRegisterForm(@Valid @ModelAttribute("register") final RegistrationForm form, final BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors() || isCaptchaWrong(form.getCaptcha())) {
            if (isCaptchaWrong(form.getCaptcha())) {
                result.addError(new FieldError("register", "captcha", "wrongcaptcha"));
            }
            return new ModelAndView("index.html");
        }

        final ServiceResult serviceResult = selfServiceRegistrationService.createOrganisation(form);

        if (!serviceResult.getStatus().is2xxSuccessful()) {
            return new ModelAndView("redirect:/register?error");
        }

        ModelAndView targetView = new ModelAndView("redirect:/register?success");
        // targetView.addObject("initname", serviceResult.get("name"));
        return targetView;
    }

    private boolean isCaptchaWrong(String captcha) {
        return !"CAR".equals(captcha);
    }
}

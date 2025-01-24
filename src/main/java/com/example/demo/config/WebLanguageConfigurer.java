package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
public class WebLanguageConfigurer implements WebMvcConfigurer {
    private static final String LANG_PARAM = "lang";

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName(LANG_PARAM);
        interceptor.setIgnoreInvalidLocale(true);
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new OnlyHardcodedLanguages();
    }

    static class OnlyHardcodedLanguages extends SessionLocaleResolver {
        private final List<Locale> allowedLocales = List.of(Locale.GERMAN, Locale.GERMANY);

        @Override
        public Locale resolveLocale(@NonNull HttpServletRequest request) {
            LocaleContextHolder.setDefaultLocale(Locale.ENGLISH);

            Locale locale = super.resolveLocale(request);
            if (!allowedLocales.contains(locale)) {
                return Locale.ENGLISH;
            }
            return locale;

        }
    }
}


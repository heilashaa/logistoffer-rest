package com.heilash.logistoffer.domain.service;

import com.heilash.logistoffer.infrastructure.CommonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Locale;

@Service
@Slf4j
public class MessageFacade {

    private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("ru");

    @Resource
    private MessageSource messageSource;
    @Resource
    private CommonData commonData;

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, Collections.emptyList().toArray(), commonData.getLocale());
        } catch (Exception e) {
            log.error("Error receiving message for code {} with locale {}", code, DEFAULT_LOCALE, e);
        }
        return code;
    }

}

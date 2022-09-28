package com.vuhien.application.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoggerUtil {
    public static final Logger loggerLogin = LogManager.getLogger("login");
    public static final Logger loggerComment = LogManager.getLogger("comment");
    public static final Logger loggerTest = LogManager.getLogger("test");
}

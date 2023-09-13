package osteam.backland.global.security.service;


import org.apache.commons.lang3.RandomStringUtils;

public class RandomKeyStringService {

    public static String generateKey(int length) {
        return RandomStringUtils.random(length, true, true);
    }
}

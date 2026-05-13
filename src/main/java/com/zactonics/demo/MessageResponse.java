package com.zactonics.demo;

/** Simple API response sent to the browser. */
public record MessageResponse(String title, String message, String status) {
}

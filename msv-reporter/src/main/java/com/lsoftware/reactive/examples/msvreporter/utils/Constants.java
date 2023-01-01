package com.lsoftware.reactive.examples.msvreporter.utils;

public interface Constants {
    String APP_STARTING = "Starting Micro-service reporter";
    String SUSPICIOUS_COLLECTION_NAME = "suspicious";
    String CHATS_COLLECTION_NAME = "chats_lines";

    String FORBIDDEN_FILE_NOT_FOUND = "Forbidden words file not found";

    String MESSAGING_SENDING_UPDATER_MESSAGE = "Sending updater message: {}";
    String MESSAGING_SENDING_SUSPICIOUS_MESSAGE = "Sending suspicious message: {}";

    String MESSAGING_ERROR_SENDING_UPDATER_MESSAGE = "Error sending updater message";
    String MESSAGING_ERROR_LOG_SENDING_UPDATER_MESSAGE = "Error sending updater message: {}";

    String SUSPICIOUS_REPORTED_REQUEST = "Suspicious reported request received {}";
    String UPDATE_REPORTED_REQUEST = "Update request received {}";

    String BAD_REQUEST_VALIDATION_ERRORS = "Bad request validation errors: {}";

    String FIELD_REQUIRED = "field is required";

    String SUCCESSFUL_RESPONSE = "Successful";
    String BAD_REQUEST = "Bad Request";


}

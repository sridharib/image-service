package com.callsign.imageservice.api;

import com.callsign.imageservice.service.ImageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageServiceAPITest {

    private ImageServiceAPI imageServiceAPI;

    @BeforeEach
    public void beforeEach() {
        imageServiceAPI = new ImageServiceAPI(new ImageServiceImpl());
    }

    @Test
    void testGetImage() throws IOException {
        ResponseEntity<InputStreamResource> responseEntity = imageServiceAPI.getImage();
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
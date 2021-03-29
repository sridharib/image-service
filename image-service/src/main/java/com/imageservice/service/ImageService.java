package com.imageservice.service;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    InputStream getImageAsStream() throws IOException;
}

package br.com.erudio.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "file")
class FileStorageConfig (var uploadDir: String = "")
package dev.portero.neon.model;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class PluginModel {
    private File file;
    private String name;
    private String version;
    private String author;
    private String description;
}

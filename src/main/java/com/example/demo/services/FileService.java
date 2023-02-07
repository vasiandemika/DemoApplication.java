package com.example.demo.services;

import com.example.demo.model.FileModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {


    private final GridFsTemplate template;
    private final GridFsOperations operations;
    private final SequenceGeneratorService sequenceGeneratorService;

    public String addFile(MultipartFile upload) throws IOException {

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", upload.getSize());
        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);

        return fileID.toString();
    }

    public FileModel downloadFile(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );

        FileModel loadFile = new FileModel();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setFilename( gridFSFile.getFilename() );

            loadFile.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }

        return loadFile;
    }

}
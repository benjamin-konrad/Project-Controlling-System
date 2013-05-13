package hm.edu.team7.backend.cloud;

import java.io.BufferedOutputStream;
import java.nio.channels.Channels;
import java.util.logging.Logger;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;

public class StorageService {

public static final String BUCKET_NAME = "csvproject";  

private FileWriteChannel writeChannel = null;
FileService fileService = FileServiceFactory.getFileService();

private BufferedOutputStream bos = null;
private static final Logger log = Logger.getLogger(StorageService.class.getName());
private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
public void init(String fileName, String mime) throws Exception {
 System.out.println("Storage service:init() method:  file name:"+fileName+" and mime:"+mime);
 log.info("Storage service:init() method:  file name:"+fileName+" and mime:"+mime);

    GSFileOptionsBuilder builder = new GSFileOptionsBuilder()
            .setAcl("public_read")
            .setBucket(BUCKET_NAME)
            .setKey(fileName)
            .setMimeType(mime);  
    AppEngineFile writableFile = fileService.createNewGSFile(builder.build());

    boolean lock = true;
    writeChannel = fileService.openWriteChannel(writableFile, lock);
    bos = new BufferedOutputStream(Channels.newOutputStream(writeChannel));
}

public void storeFile(byte[] b, int readSize) throws Exception { 
    bos.write(b,0,readSize);
    bos.flush();
}

    public void destroy() throws Exception {
     log.info("Storage service: destroy() method");
        bos.close();
        writeChannel.closeFinally();
    }
    public BlobKey getBlobkey (String filename) {
        BlobKey bk = blobstoreService.createGsBlobKey("/gs/"+ BUCKET_NAME +"/"+filename);
        return bk;
    }

}
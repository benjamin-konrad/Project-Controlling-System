package hm.edu.team7.backend.cloud;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.appengine.api.blobstore.BlobKey;

/*upload.hmtl
 * <html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Hello App Engine</title>
</head>

<body>
    <h1>Hello App Engine!</h1>
    <form action="/upload" method="post" enctype="multipart/form-data">
        <p>File<input type="file" name="file" /> </p>
        <p> <input type="submit"value="upload" /> <input type="reset" value="reset"/> </p>
    </form>
  </body>
</html>
 */
/*web.xml
 * <?xml version="1.0" encoding="utf-8" standalone="no"?><web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.5" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
    <servlet>
        <servlet-name>upload</servlet-name>
        <servlet-class>storefile.UploadServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>upload</servlet-name>
        <url-pattern>/upload</url-pattern>
    </servlet-mapping>
    <servlet>
        <servlet-name>serve</servlet-name>
        <servlet-class>storefile.fileserve</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>serve</servlet-name>
        <url-pattern>/serve</url-pattern>
    </servlet-mapping>
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
 */
public class UploadServlet extends HttpServlet{

   private static final long serialVersionUID = 1L;
  private StorageService storage = new StorageService();
  private static int BUFFER_SIZE =1024 * 1024* 10;
  @Override
   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

      resp.setContentType("text/plain");
      resp.getWriter().println("Now see here your file content, that you have uploaded on storage..");

      ServletFileUpload upload = new ServletFileUpload();
      FileItemIterator iter; 
   try {
    iter = upload.getItemIterator(req);
     while (iter.hasNext()) {
            FileItemStream item = iter.next();
            String fileName = item.getName();
            String mime = item.getContentType();

            storage.init(fileName, mime);
            InputStream is = new BufferedInputStream(item.openStream());

             byte[] b = new byte[BUFFER_SIZE];
            int readBytes = is.read(b, 0, BUFFER_SIZE);

            while (readBytes != -1) {
                storage.storeFile(b, readBytes);
                readBytes = is.read(b, 0, BUFFER_SIZE);
            }

             is.close();
            storage.destroy();

       resp.getWriter().println("File uploading done");

            // resp.getWriter().println("READ:" + storage.readTextFileOnly(fileName));
            BlobKey key = storage.getBlobkey(fileName);
            if (key != null) {
                resp.sendRedirect("/serve?blob-key=" + key.getKeyString());
            } else {
                resp.sendRedirect("/");
            }


      }
   } catch (Exception e) {
    e.printStackTrace(resp.getWriter());
    System.out.println("Exception::"+e.getMessage());
    e.printStackTrace();
   }
 }

}
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FileAccess
{
    private FileSystem hdfs;
    private String rootPath;

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     * for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) throws URISyntaxException, IOException {

        this.rootPath = rootPath;
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");
        hdfs = FileSystem.get(new URI(rootPath), configuration);
    }


    public OutputStream createForWriting(String path) throws IOException {
        return hdfs.create(new Path(rootPath + "/" + path));
    }
    /**
     * Creates empty file or directory
     *
     * @param path
     */

    public void create(String path) throws IOException {

        Path hdfsPath = new Path(rootPath + "/" + path);

        if (hdfs.exists(hdfsPath)) {
            hdfs.createNewFile(new Path(rootPath + "/" + path));
        }
        else System.out.println("###  File " + path + " does not exists  ###");
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content)
    {
        Path hdfsPath = new Path(rootPath + "/" + path);

        try ( FSDataOutputStream fsDataOS = hdfs.append(hdfsPath)) {
            fsDataOS.writeBytes(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException {

        Path hdfsPath = new Path(rootPath + "/" + path);
        StringBuilder out = new StringBuilder();

        if (hdfs.exists(hdfsPath)) {
            FSDataInputStream dataIn = hdfs.open(hdfsPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(dataIn));

            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
        }
        else out.append("###  File ").append(path).append(" does not exist  ###");
        return out.toString();
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException {

        Path hdfsPath = new Path(rootPath + "/" + path);
        if (!hdfs.exists(hdfsPath)) {
            hdfs.delete((hdfsPath), true);
        }
        else {
            System.out.println("###  File " + path + " does not exist  ###");
        }
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws IOException {
        return hdfs.isDirectory(new Path(rootPath + "/" + path));
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(Path path) throws IOException {

        List<String> fileList = new ArrayList<>();
        FileStatus[] fileStatus = hdfs.listStatus(path);
        for (FileStatus fileStat : fileStatus) {
            if (fileStat.isDirectory()) {
                fileList.addAll(list(fileStat.getPath()));
            } else {
                fileList.add(fileStat.getPath().toString());
            }
        }
        return fileList;
    }

    public void ifExistDel(String path) throws IOException {
        Path file = new Path(rootPath + "/" + path);
        if (hdfs.exists(file)) {
            delete(path);
        }
    }
    public void close() throws IOException {
        hdfs.close();
    }
}
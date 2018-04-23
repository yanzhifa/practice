package com.ldy.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by yanz3 on 9/28/16.
 */
public class ZipTest {

    public static final String MYSTIC_DC_PATH = "/Users/yanz3/Desktop/sanity-test/";

    public static void main(String[] args) throws IOException {
        List<File> filesToZip = new ArrayList<>();
        File logfile = new File("/Users/yanz3/Desktop/sanity-test/test");
        System.out.println(logfile.getParent() + File.separator + "name");
        //logfile.renameTo(new File(logfile.getParent() + File.separator + "zhifa.tar.gz"));
        filesToZip.add(logfile);
        System.out.println(zipFiles(filesToZip));
    }

    private static String zipFiles(List<File> filesToZip) {
        String logBundleFileName = getLogBundleFileName();
        File zipFiles = zip(filesToZip, MYSTIC_DC_PATH, MYSTIC_DC_PATH + logBundleFileName);
        if (zipFiles == null || !zipFiles.exists())
        {
            System.out.println("Fail to generate log bundle");
            return null;
        }
        return logBundleFileName;
    }

    private static String getLogBundleFileName() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return "VxRail_Support_Bundle_" + df.format(new Date()) + ".zip";
    }
    private static File zip(List<File> files, String baseDir, String filename) {

        File zipfile = new File(filename);
        String targetZipFileName = getFileNameFromFilePath(filename);
        String subFolderName = targetZipFileName.substring(0, targetZipFileName.lastIndexOf("."));
        // Create a buffer for reading the files
        byte[] buf = new byte[1024];
        try {
            // create the ZIP file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            // compress the files
            for (int i = 0; i < files.size(); i++) {
                FileInputStream in = new FileInputStream(files.get(i).getCanonicalPath());
                // add ZIP entry to output stream
                out.putNextEntry(new ZipEntry("/" + subFolderName + "/" + getRelativePath(files.get(i), baseDir)));
                // transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // complete the entry
                out.closeEntry();
                in.close();
            }
            // complete the ZIP file
            out.close();
            //if zip succeeds, delete all *.tar.gz
//            for(int i = 0; i < files.size(); i++){
//                File file = files.get(i);
//                file.delete();
//                System.out.println(file.getName() + " is deleted after zipping");
//            }
        } catch (IOException e) {
            System.out.println("zipping log bundles together failed due to IO Exception");
        } catch (Exception e) {
            System.out.println("zipping log bundles together failed due to unknown exception");
        }
        return zipfile;
    }

    private static String getFileNameFromFilePath(String filePath) {
        if (filePath != null) {
            return filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.length());
        }
        else {
            return null;
        }
    }

    private static String getRelativePath(File file, String folderPath) {
        String filePath = file.getAbsolutePath();
        if (folderPath != null && filePath.startsWith(folderPath)) {
            return filePath.substring(folderPath.length());
        } else {
            return file.getName();
        }
    }
}

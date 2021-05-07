package market.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateWord {

    public static void main(String[] args)throws Exception {
        String templatePath = "D:\\Desktop\\监督性检查单.doc";
        InputStream is = new FileInputStream(templatePath);
        HWPFDocument doc = new HWPFDocument(is);
        Range range = doc.getRange();
        //把range范围内的${reportDate}替换为当前的日期
        range.replaceText("${epName}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        OutputStream os = new FileOutputStream("D:\\Desktop\\3.doc");
        //把doc输出到输出流中
        doc.write(os);
        CreateWord createWord=new CreateWord();
        createWord.closeStream(os);
        createWord.closeStream(is);
    }

        /**
         * 关闭输入流
         * @param is
         */
    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        /**
         * 关闭输出流
         * @param os
         */
        private void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

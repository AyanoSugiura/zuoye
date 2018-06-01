package zhou.zuoye.util;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class PdfConverter {
    /**
     * 转换类型
     */
    public static final int TYPE_WORD = 0;
    public static final int TYPE_EXCEL = 1;
    public static final int TYPE_PPT = 2;

    /**
     * 转换类型
     */
    public static  Integer converterType ;
    /**
     * 源文件(路径 + 文件名)
     */
    public static  String  source;
    /**
     * 目标文件(路径 + 文件名)
     */
    public static  String  dest;


    /*public static void main(String[] args) {

        try {
            init(); //初始化参数配置
            converter();//启动转换功能
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public static void init(int type,String source,String dest){
        PdfConverter.converterType = type;
        PdfConverter.source = source ;
        PdfConverter.dest = dest ;
    }

    public static void converter() throws Exception{
        File file = new File(source);

        if(!file.exists()){
            throw new Exception("转换文件不存在");
        }

        switch(converterType){
            case TYPE_WORD :
                word2Pdf(source,dest);
                break;
            case TYPE_EXCEL :
                excel2Pdf(source,dest);
                break;
            case TYPE_PPT :
                ppt2Pdf(source,dest);
                break;
            default:
                throw new Exception("文件转换类型不存在");
        }
    }

    public static void word2Pdf(String sourcePath, String destPath)
            throws Exception {

        ActiveXComponent word = null;
        Dispatch documents = null;

        try {
            //初始化线程
            ComThread.InitMTA(true);

            //初始化应用
            word = new ActiveXComponent("Word.Application");

            //设置应用属性
            Dispatch.put(word.getObject(), "Visible", new Variant(false));
            Dispatch.put(word.getObject(), "DisplayAlerts", new Variant(0));

            //通过应用打开文档
            documents = word.invokeGetComponent("Documents")
                    .invokeGetComponent("Open", new Variant(sourcePath));
            //调用对应的方法
            Dispatch.invoke(documents,
                    "SaveAs",
                    Dispatch.Method,
                    new Object[] {destPath , new Variant(17) },
                    new int[1]);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                //退出应用
                if (word != null) {
                    word.invoke("Quit", new Variant(false));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //关闭文档和应用
                if (documents != null)
                    documents.safeRelease();
                if (word != null)
                    word.safeRelease();

                //释放线程
                ComThread.Release();
            }
        }
    }

    public static void excel2Pdf(String sourcePath, String destPath)
            throws Exception {

        ActiveXComponent excel = null;
        Dispatch workBooks = null;
        try {
            ComThread.InitMTA(true);
            excel = new ActiveXComponent("Excel.Application");
            Dispatch.put(excel.getObject(), "Visible", new Variant(false));
            Dispatch.put(excel.getObject(), "DisplayAlerts", new Variant(false));

            workBooks = excel.invokeGetComponent("Workbooks")
                    .invokeGetComponent("Open", new Variant(sourcePath));

            Dispatch.invoke(workBooks,
                    "ExportAsFixedFormat",
                    Dispatch.Method,
                    new Variant[]{new Variant(0),new Variant(destPath)},
                    new int[1]);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (excel != null) {
                    excel.invoke("Quit");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (workBooks != null)
                    workBooks.safeRelease();
                if (excel != null)
                    excel.safeRelease();

                ComThread.Release();
            }
        }
    }

    public static void ppt2Pdf(String sourcePath, String destPath)
            throws Exception {

        ActiveXComponent ppt = null;
        Dispatch presentations = null;
        try {
            ComThread.InitMTA(true);
            ppt = new ActiveXComponent("PowerPoint.application");
            Dispatch.put(ppt.getObject(), "DisplayAlerts", new Variant(false));

            presentations = ppt.invokeGetComponent("Presentations")
                    .invokeGetComponent("Open", new Variant(sourcePath));

            Dispatch.invoke(presentations,
                    "SaveAs",
                    Dispatch.Method,
                    new Object[] {destPath , new Variant(32) },
                    new int[1]);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (ppt != null) {
                    ppt.invoke("Quit");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (presentations != null)
                    presentations.safeRelease();
                if (ppt != null)
                    ppt.safeRelease();

                ComThread.Release();
            }
        }
    }
}

package RSR.Core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class RSRClassLoader extends ClassLoader{

    private String path;

    public RSRClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        byte[] byteClass = getByteClass();
        if (byteClass == null) {
//            log
            return null;
        }
        clazz = defineClass(name, byteClass, 0, byteClass.length);
        return clazz;
    }

    private byte[] getByteClass() {
        File file = new File(path);
        if (file.exists()){
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = in.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            return out.toByteArray();
        }else{
            return null;
        }

    }
}

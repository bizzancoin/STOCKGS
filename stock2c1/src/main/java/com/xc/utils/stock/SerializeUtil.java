package com.xc.utils.stock;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SerializeUtil {
    /**

     * 把java对象序列化成byte数组

     * @author LAN

     * @date 2018年11月13日

     * @param object

     * @return

     */

    public static byte[] serialize(Object object) {

        if(object==null) {

            return null;

        }

        ByteArrayOutputStream baos = null;

        Output output = null;

        try {

            Kryo kryo = new Kryo();

            baos = new ByteArrayOutputStream();

            output = new Output(baos);

            kryo.writeObject(output, object);

            output.flush();

            return baos.toByteArray();

        }  finally {

            try {

                if(baos!=null) baos.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

            output.close();

        }

    }



    /**

     * 把byte数组反序列化得到java对象

     * @author LAN

     * @date 2018年11月13日

     * @param bytes

     * @param clazz

     * @return

     */

    public  static<T> T unserialize(byte[] bytes, Class clazz) {

        if(bytes==null || bytes.length==0) {

            return null;

        }

        Kryo kryo = new Kryo();

        Input input = new Input(bytes);

        T obj = (T) kryo.readObject(input, clazz);

        input.close();

        return obj;

    }
}

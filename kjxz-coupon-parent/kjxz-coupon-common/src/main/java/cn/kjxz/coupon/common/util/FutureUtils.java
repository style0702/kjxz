package cn.kjxz.coupon.common.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * future返回值异常工具类
 * @param <T>
 */
public class FutureUtils<T> {
    public static<T> T get(Future<T> future){
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

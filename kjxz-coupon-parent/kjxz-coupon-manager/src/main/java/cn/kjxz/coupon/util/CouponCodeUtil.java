package cn.kjxz.coupon.util;

import cn.kjxz.coupon.common.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/**
 * 生成优惠劵码
 */
@Component
public class CouponCodeUtil {
    @Autowired
    private IdUtils idUtils;
    @Autowired
    private Executor getAsyncExecutor;

    public List<String> generateCode(int count, String id){
        CopyOnWriteArrayList<String> objects = new CopyOnWriteArrayList<>();
        int halfCount = count/2;
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < halfCount; i++) {
                objects.add(idUtils.getIdNoAsync(id));
            }
        }, getAsyncExecutor);
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < count - halfCount; i++) {
                objects.add(idUtils.getIdNoAsync(id));
            }
        }, getAsyncExecutor);
        CompletableFuture.allOf(voidCompletableFuture,voidCompletableFuture1).join();
        return objects;
    }
}

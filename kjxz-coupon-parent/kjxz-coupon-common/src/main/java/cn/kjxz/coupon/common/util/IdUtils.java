package cn.kjxz.coupon.common.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class IdUtils {
    private final String strings[] = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
            "1","2","3","4","5","6","7","8","9"};
    @Async("getAsyncExecutor")
    public Future<String> getId(String id){
        StringBuilder finalld = new StringBuilder("");
        StringBuilder stringBuilder = new StringBuilder(id);
        //取前三位后三位
        String substring = stringBuilder.substring(0, 3);
        String substring1 = stringBuilder.substring(stringBuilder.length() - 4);
        //前六位  第七位+第八位*第九位/3 作为放库的依据
        Random random = new Random();
        int i = random.nextInt(10);
        int j = random.nextInt(10);
        int k = random.nextInt(10);
        List<String> collect = Stream.of(strings).collect(Collectors.toList());
        Collections.shuffle(collect);
        String collect1 = collect.stream().collect(Collectors.joining());
        finalld.append(substring).append(substring1).append(i).append(j).append(k).append(collect1);
        return new AsyncResult<>(finalld.toString());

    }

    public String getIdNoAsync(String id){
        StringBuilder finalId = new StringBuilder("");
        StringBuilder stringBuilder = new StringBuilder(id);
        //比如要取前三位后三位
        String substring = stringBuilder.substring(0, 3);
        String substring1 = stringBuilder.substring(stringBuilder.length() - 4);
        //前六位  第七位+第八位*第九位/3 作为放库的依据
        Random random = new Random();
        int i = random.nextInt(10);
        int j = random.nextInt(10);
        int k = random.nextInt(10);
        List<String> collect = Stream.of(strings).collect(Collectors.toList());
        Collections.shuffle(collect);
        String collect1 = collect.stream().collect(Collectors.joining());
        finalId.append(substring).append(substring1).append(i).append(j).append(k).append(collect1);
        return finalId.toString();
    }
}

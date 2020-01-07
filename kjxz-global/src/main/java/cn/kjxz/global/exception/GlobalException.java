package cn.kjxz.global.exception;

import lombok.Data;

@Data
public class GlobalException extends RuntimeException{
    private String message;

    public GlobalException(String message){
        super(message);
        this.message= message;
    }
}

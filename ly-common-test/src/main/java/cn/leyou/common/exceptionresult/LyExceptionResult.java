package cn.leyou.common.exceptionresult;

import cn.leyou.common.enums.ExceptionEnum;
import lombok.Data;
import lombok.Getter;

@Getter
public class LyExceptionResult {
    private Integer status;
    private String message;
    private Long timestamp;
    public LyExceptionResult(ExceptionEnum exceptionEnum){
        this.status=exceptionEnum.getCode();
        this.message=exceptionEnum.getMsg();
        this.timestamp=System.currentTimeMillis();
    }
}

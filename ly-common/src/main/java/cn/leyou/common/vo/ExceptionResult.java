package cn.leyou.common.vo;

import cn.leyou.common.enums.ExceptionEnum;
import lombok.Data;

@Data
public class ExceptionResult {
    private Integer status;
    private String message;
    private Long timestamp;
    public ExceptionResult(ExceptionEnum exceptionEnum){
        this.status=exceptionEnum.getCode();
        this.message=exceptionEnum.getMsg();
        this.timestamp= System.currentTimeMillis();
    }
}

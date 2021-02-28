package cn.leyou.common.advice;

import cn.leyou.common.exception.LyException;
import cn.leyou.common.exceptionresult.LyExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LyExceptionAdvice {
    @ExceptionHandler(LyException.class)
    public ResponseEntity<LyExceptionResult> handlerException(LyException e){
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new LyExceptionResult(e.getExceptionEnum()));
    }
}

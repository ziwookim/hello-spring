package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class TimeTraceAop {

//    @Around("execution(* hello.hellospring..*(..))")
    // SpringConfig에서 명시적으로 Bean 주입하는 경우, 자기 자신 (TimeTraceAop 파일 제외 하여 순환 참조 문제 막음.)
//    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    @Around("execution(* hello.hellospring.service..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws  Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + " ms");
        }
    }
}

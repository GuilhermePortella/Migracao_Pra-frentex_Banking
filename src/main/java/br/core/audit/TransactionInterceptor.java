package br.core.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
public class TransactionInterceptor {

   private static final Logger logger = LoggerFactory.getLogger(TransactionInterceptor.class);

   @Around("@annotation(br.service.audit.LoggedTransaction)")
   public Object logTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
      LocalDateTime inicio = LocalDateTime.now();

      MethodSignature signature = (MethodSignature) joinPoint.getSignature();
      String methodName = signature.getMethod().getName();
      String className = joinPoint.getTarget().getClass().getSimpleName();

      try {
         logger.info("TRANSACTION_START | Method: {} | Class: {} | Time: {}",
               methodName, className, inicio);
         Object resultado = joinPoint.proceed();

         LocalDateTime fim = LocalDateTime.now();
         Duration duracao = Duration.between(inicio, fim);

         logger.info("TRANSACTION_END | Method: {} | Class: {} | Duration: {}ms | Success: true",
               methodName, className, duracao.toMillis());

         return resultado;
      } catch (Throwable e) {
         logger.error("TRANSACTION_ERROR | Method: {} | Class: {} | Error: {}",
               methodName, className, e.getMessage(), e);
         throw e;
      }
   }

}